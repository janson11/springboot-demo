package com.janson.netty.nio.ReactorModel;

import com.janson.netty.common.util.Dateutil;
import com.janson.netty.common.util.ThreadUtil;
import com.janson.netty.nio.NioDemoConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/9 4:07 下午
 */
@Slf4j
public class EchoClient {

    public static void main(String[] args) throws IOException {
        new EchoClient().start();
    }

    public void start() throws IOException {
        InetSocketAddress address = new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_IP, NioDemoConfig.SOCKET_SERVER_PORT);

        SocketChannel socketChannel = SocketChannel.open(address);
        log.info("客户端连接成功");
        socketChannel.configureBlocking(false);
        socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
        while (!socketChannel.finishConnect()) {

        }
        log.info("客户端启动成功");
        // 启动接受线程
        Processor processor = new Processor(socketChannel);
        Commander commander = new Commander(processor);

        new Thread(commander).start();
        new Thread(processor).start();

    }


    static class Commander implements Runnable {
        Processor processor;

        public Commander(Processor processor) {
            this.processor = processor;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                ByteBuffer buffer = processor.getSendBuffer();
                Scanner scanner = new Scanner(System.in);
                while (processor.hasData.get()) {
                    log.info("还有消息没有发送完，请稍等");
                    ThreadUtil.sleepMilliSeconds(1000);
                }
                log.info("请输入发送内容:");
                if (scanner.hasNext()) {
                    String next = scanner.next();
                    buffer.put((Dateutil.getNow() + " >>" + next).getBytes());
                    processor.hasData.set(true);
                }
            }
        }
    }

    @Data
    static class Processor implements Runnable {
        ByteBuffer sendBuffer = ByteBuffer.allocate(NioDemoConfig.SEND_BUFFER_SIZE);
        ByteBuffer readBuffer = ByteBuffer.allocate(NioDemoConfig.SEND_BUFFER_SIZE);

        protected AtomicBoolean hasData = new AtomicBoolean(false);

        final Selector selector;
        final SocketChannel channel;

        Processor(SocketChannel channel) throws IOException {
            // Reactor初始化
            selector = Selector.open();
            this.channel = channel;
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterator<SelectionKey> it = selected.iterator();
                    while (it.hasNext()) {
                        SelectionKey sk = it.next();
                        if (sk.isWritable()) {
                            if (hasData.get()) {
                                SocketChannel socketChannel = (SocketChannel) sk.channel();
                                sendBuffer.flip();
                                // 发送数据
                                socketChannel.write(sendBuffer);
                                sendBuffer.clear();
                                hasData.set(false);
                            }
                        }

                        if (sk.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) sk.channel();
                            int length = 0;
                            while ((length = socketChannel.read(readBuffer)) > 0) {
                                readBuffer.flip();
                                log.info("server echo:{}", new String(readBuffer.array(), 0, length));
                                readBuffer.clear();
                            }
                        }
                    }
                    selected.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

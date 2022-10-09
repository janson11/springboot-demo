package com.janson.netty.nio.ReactorModel;

import com.janson.netty.nio.NioDemoConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: 反应器
 * @Author: shanjian
 * @Date: 2022/10/9 3:34 下午
 */
@Slf4j
public class EchoServerReactor implements Runnable{

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReactor()).start();
    }

    Selector selector;
    ServerSocketChannel serverSocket;

    EchoServerReactor() throws IOException {
        // reactor初始化
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_IP,NioDemoConfig.SOCKET_SERVER_PORT);

        //非阻塞
        serverSocket.configureBlocking(false);

        //分布处理,第一步 接收accept事件
        SelectionKey sk = serverSocket.register(selector, 0, new AcceptorHandler());

        serverSocket.socket().bind(address);
        log.info("服务端已经开始监听：{}",address);
        sk.interestOps(SelectionKey.OP_ACCEPT);



    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select(1000);

                Set<SelectionKey> selected = selector.selectedKeys();
                if(null ==selected ||selected.size()==0) {
                    continue;
                }
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    // reactor负责dispatch接收的事件
                    SelectionKey sk = it.next();
                    it.remove();
                    dispatch(sk);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey sk) {
        Runnable handler = (Runnable)sk.attachment();
        // 调用之前attach绑定到选择键的handler处理器对象
        if (handler!=null) {
            handler.run();
        }
    }

    // handler 新连接处理器
    class AcceptorHandler implements Runnable{
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();
                log.info("接收到一个连接");
                if (channel!=null) {
                    new EchoHandler(selector,channel);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

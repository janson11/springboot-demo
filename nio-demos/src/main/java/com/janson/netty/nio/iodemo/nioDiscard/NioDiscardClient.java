package com.janson.netty.nio.iodemo.nioDiscard;

import com.janson.netty.nio.NioDemoConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Description: 客户端
 * @Author: shanjian
 * @Date: 2022/10/9 10:17 上午
 */
@Slf4j
public class NioDiscardClient {

    public static void main(String[] args) throws Exception {
        startClient();
    }

    public static void startClient() throws IOException {
        InetSocketAddress address = new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_IP,NioDemoConfig.SOCKET_SERVER_PORT);

        // 1、获取通道(channel)
        SocketChannel socketChannel = SocketChannel.open(address);
        //2、切换成非阻塞模式
        socketChannel.configureBlocking(false);
        // 不断的自旋、等待连接完成，或者做一些其他的事情
        while (!socketChannel.finishConnect()) {

        }

        log.info("客户端连接成功");
        // 3、分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello world".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        log.info("客户端写入成功");

        socketChannel.shutdownOutput();
        socketChannel.close();

    }
}

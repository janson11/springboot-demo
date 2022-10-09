package com.janson.netty.nio.iodemo.OIO;

import com.janson.netty.nio.NioDemoConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: 服务端： 一个线程处理一个连接
 * @Author: shanjian
 * @Date: 2022/10/9 2:57 下午
 */
@Slf4j
public class ConnectionPerThread implements Runnable{
    public static void main(String[] args) {
        new ConnectionPerThread().run();
    }

    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(NioDemoConfig.SOCKET_SERVER_PORT);
            log.info("server is up");

            while (!Thread.interrupted()) {
                // 每接收一个客户端的socket连接，创建一个线程,进行阻塞式的读写
                Socket socket = serverSocket.accept();

                MyHandler myHandler = new MyHandler(socket);
                //创建新线程来处理handler
                new Thread(myHandler).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * 自定义处理器
     */
    static class MyHandler implements Runnable {
        final Socket socket;

        MyHandler(Socket socket) {
            this.socket = socket;
            log.info("连接的两个端口 port:{} localPort:{}",socket.getPort(),socket.getLocalPort());
        }

        @Override
        public void run() {
            while (true) {
                try {
                    byte[] input = new byte[NioDemoConfig.SERVER_BUFFER_SIZE];
                    //读取数据
                    socket.getInputStream().read(input);

                    log.info("收到:{}",new String(input));

                    // 处理业务逻辑，获取处理结果
                    byte[] output = input;
                    //写入结果
                    socket.getOutputStream().write(output);
                } catch (IOException e){
                e.printStackTrace();
            }
            }
        }
    }
}

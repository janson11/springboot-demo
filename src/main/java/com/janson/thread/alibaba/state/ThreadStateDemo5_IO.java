package com.janson.thread.alibaba.state;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/31 4:56 下午
 */
public class ThreadStateDemo5_IO {
    public static void main(String[] args) throws InterruptedException {
        Charset charset = Charset.forName("UTF-8");
        Thread t1 = new Thread( () -> {
            try(ServerSocket ss= new ServerSocket(9000)) {
                while (true) {
                    try {
                        System.out.println("t1 即将接收连接...");
                        Socket s =ss.accept();
                        System.out.println("t1 接收到连接...");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream(),charset));
                        String mess = null;
                        System.out.println("t1 将接收连接的数据...");
                        while ((mess=reader.readLine())!=null) {
                            System.out.println(mess);
                        }
                        s.close();

                    }catch (IOException e1) {
                         e1.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        Thread.sleep(3000L);
        System.out.println("t1的状态 0："+t1.getState());
        Thread.sleep(20000L);
        System.out.println("t1的状态 1："+t1.getState());

    }
}

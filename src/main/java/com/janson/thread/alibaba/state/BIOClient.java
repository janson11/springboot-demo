package com.janson.thread.alibaba.state;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/31 5:31 下午
 */
public class BIOClient extends Thread{

    private String host;
    private int port;

    public BIOClient(String host,int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try(Socket s= new Socket(host,port); OutputStream out =s.getOutputStream()) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入:");
            String mess = scanner.nextLine();
            out.write(mess.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BIOClient bioClient = new BIOClient("localhost",9000);
        bioClient.start();
    }
}

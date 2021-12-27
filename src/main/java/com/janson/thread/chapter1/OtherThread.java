package com.janson.thread.chapter1;

/**
 * @Description: 其他方法
 * @Author: Janson
 * @Date: 2021/12/27 8:36
 **/
public class OtherThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();


        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }

}

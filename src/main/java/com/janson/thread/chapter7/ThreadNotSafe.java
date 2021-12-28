package com.janson.thread.chapter7;

/**
 * @Description: 共享的变量或资源带来的线程安全问题
 * @Author: shanjian
 * @Date: 2021/12/28 10:09 上午
 */
public class ThreadNotSafe {

    static int i;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10000; j++) {
                    i++;
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);


    }
}

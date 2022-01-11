package com.janson.thread.chapter25;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/8 9:55
 **/
public class ValueAtomicDemo implements Runnable {
    static AtomicInteger value = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new ValueAtomicDemo();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(value);

    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            value.incrementAndGet();
        }
    }
}

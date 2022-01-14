package com.janson.thread.chapter37;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/13 22:37
 **/
public class DontVolatile implements Runnable {

    volatile int a;
    AtomicInteger realA = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException {
        DontVolatile r = new DontVolatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.a);
        System.out.println(r.realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}

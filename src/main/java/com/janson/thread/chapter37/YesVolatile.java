package com.janson.thread.chapter37;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 布尔标记位
 * @Author: shanjian
 * @Date: 2022/1/14 10:20 上午
 */
public class YesVolatile  implements Runnable{

    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        YesVolatile r = new YesVolatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.done);
        System.out.println(r.realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
         setDone();
         realA.incrementAndGet();
        }
    }

    private void setDone() {
        done =true;
    }
}

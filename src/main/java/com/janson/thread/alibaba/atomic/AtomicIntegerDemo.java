package com.janson.thread.alibaba.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/18 8:54 上午
 */
public class AtomicIntegerDemo {
    static AtomicInteger count = new AtomicInteger(0);

    public static void increase() {
        count.getAndIncrement();
    }

    public static void main(String[] args) {
        int threads = 20;
        CountDownLatch cdl = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    AtomicIntegerDemo.increase();
                }
                cdl.countDown();
            }).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(AtomicIntegerDemo.count.get());
    }


}

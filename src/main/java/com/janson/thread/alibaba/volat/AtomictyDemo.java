package com.janson.thread.alibaba.volat;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: volatile 不能保证线程安全。
 * @Author: shanjian
 * @Date: 2022/4/14 11:20 上午
 */
public class AtomictyDemo {

    static volatile int count =0;

    public static void increase() {
        count++;
    }

    public static void main(String[] args) {
        int threads =20;
        // 倒计数锁存器，并发协同用
        CountDownLatch cdl = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 10000; i1++) {
                        AtomictyDemo.increase();
                    }
                    cdl.countDown();
                }
            }).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(AtomictyDemo.count);
    }
}

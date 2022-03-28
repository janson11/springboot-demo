package com.janson.thread.alibaba.pool;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/28 4:19 下午
 */
public class MaxThreadsDemo {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(1);
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 4000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            System.out.println("i=" + i);
        }
    }


}

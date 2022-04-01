package com.janson.thread.alibaba.stop;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/1 10:28 上午
 */
public class StopReleaseLock {
    public static void main(String[] args) {
        final Object lock = new Object();
        try {
            Thread t0 = new Thread(() -> {
                try {
                    synchronized (lock) {
                        System.out.println("thread->" + Thread.currentThread().getName() + " acquire lock:");
                        Thread.sleep(3000);
                        System.out.println("thread->" + Thread.currentThread().getName() + " release lock:");

                    }
                } catch (Exception e) {
                    System.out.println("Caught in run" + e);
                    e.printStackTrace();
                }
            });

            Thread t1 = new Thread() {
                @Override
                public void run() {
                    synchronized (lock) {
                        System.out.println("thread->" + Thread.currentThread().getName() + " acquire lock:");
                    }
                }
            };

            t0.start();
            Thread.sleep(100);
            t1.start();


        } catch (Exception e1) {
            System.out.println("Caught in main " + e1);
            e1.printStackTrace();
        }
    }
}
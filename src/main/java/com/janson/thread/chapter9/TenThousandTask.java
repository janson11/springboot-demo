package com.janson.thread.chapter9;

/**
 * @Description: for循环新建10000个线程
 * @Author: shanjian
 * @Date: 2021/12/28 10:49 上午
 */
public class TenThousandTask {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("cost time：" + (endTime - startTime));
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }

}

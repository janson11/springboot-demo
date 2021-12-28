package com.janson.thread.chapter10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 用固定线程数的线程池执行10000个任务
 * @Author: shanjian
 * @Date: 2021/12/28 11:02 上午
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            service.execute(new Task());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("cost1 time：" + (endTime - startTime));
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }
}

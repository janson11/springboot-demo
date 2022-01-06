package com.janson.thread.chapter24;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 在16个线程下使用AtomicLong
 * @Author: Janson
 * @Date: 2022/1/6 23:30
 **/
public class AtomicLongDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService service = Executors.newFixedThreadPool(16);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            service.submit(new Task(counter));
        }
        long end = System.currentTimeMillis();
        System.out.println("cost time:" + (end - start) + "ms");
        Thread.sleep(2000);
        System.out.println(counter.get());
    }

    static class Task implements Runnable {
        private final AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.incrementAndGet();
        }
    }
}

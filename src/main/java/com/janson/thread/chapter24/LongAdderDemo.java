package com.janson.thread.chapter24;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description: 在16个线程下使用LongAdder
 * @Author: Janson
 * @Date: 2022/1/6 23:30
 **/
public class LongAdderDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAdder counter = new LongAdder();
        ExecutorService service = Executors.newFixedThreadPool(16);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            service.submit(new Task(counter));
        }
        long end = System.currentTimeMillis();
        System.out.println("cost time:" + (end - start) + "ms");
        Thread.sleep(2000);
        System.out.println(counter.sum());
    }

    static class Task implements Runnable {
        private final LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.increment();
        }
    }
}

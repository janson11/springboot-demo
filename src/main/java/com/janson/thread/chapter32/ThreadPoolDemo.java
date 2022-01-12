package com.janson.thread.chapter32;

import lombok.SneakyThrows;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/12 8:47
 **/
public class ThreadPoolDemo {

    ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
        System.out.println(threadPoolDemo.getPrices());
    }

    private Set<Integer> getPrices() throws InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        threadPool.submit(new Task(123, prices));
        threadPool.submit(new Task(456, prices));
        threadPool.submit(new Task(789, prices));
        Thread.sleep(3000);
        return prices;
    }


    private class Task implements Runnable {
        Integer productId;
        Set<Integer> prices;

        public Task(Integer productId, Set<Integer> prices) {
            this.productId = productId;
            this.prices = prices;
        }

        @SneakyThrows
        @Override
        public void run() {
            int price = 0;
            Thread.sleep((long) Math.random() * 4000);
            price = (int) (Math.random() * 4000);
            prices.add(price);
        }
    }
}

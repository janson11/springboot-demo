package com.janson.thread.chapter32;

import lombok.SneakyThrows;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description: CompletableFuture 实现旅游平台问题
 * @Author: shanjian
 * @Date: 2022/1/12 5:24 下午
 */
public class CompletableFutureDemo {


    public static void main(String[] args) throws InterruptedException {
        CompletableFutureDemo completableFutureDemo = new CompletableFutureDemo();
        System.out.println(completableFutureDemo.getPrices());
    }

    private Set<Integer> getPrices() throws InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(new Task(123,prices));
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(new Task(456,prices));
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(new Task(789,prices));
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
        try {
            allTasks.get(3, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
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

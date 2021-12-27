package com.janson.thread.chapter2;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/12/27 5:14 下午
 */
public class ProducerConsumerTest1 {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(8);
        Producer1 producer = new Producer1(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(500);

        Consumer1 consumer = new Consumer1(storage);
        while (consumer.needMoreNums()) {
            System.out.println("consumer线程编号：" + Thread.currentThread().getName()+"--"+consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        producerThread.interrupt();
        System.out.println(producerThread.isInterrupted());
    }
}

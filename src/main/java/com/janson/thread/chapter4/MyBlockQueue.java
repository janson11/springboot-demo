package com.janson.thread.chapter4;

import java.util.LinkedList;

/**
 * @Description: 如何用 wait/notify 实现生产者消费者模式
 * @Author: shanjian
 * @Date: 2021/12/27 6:39 下午
 */
public class MyBlockQueue {

    private int maxSize;
    private LinkedList<Object> storage;

    public MyBlockQueue(int size) {
        this.maxSize = size;
        storage = new LinkedList<>();
    }

    public synchronized void put() throws InterruptedException {
        while (storage.size() == maxSize) {
            wait();
        }

        Object o = new Object();
        storage.add(o);
        System.out.println("put ：" + o.toString());
        notifyAll();
    }

    public synchronized void take() throws InterruptedException {
        while (storage.size() == 0) {
            wait();
        }
        System.out.println("take :" + storage.remove());
        notifyAll();
    }
}

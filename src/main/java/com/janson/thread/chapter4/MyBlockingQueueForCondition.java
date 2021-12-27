package com.janson.thread.chapter4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 如何用 Condition 实现生产者消费者模式
 * @Author: shanjian
 * @Date: 2021/12/27 6:25 下午
 */
public class MyBlockingQueueForCondition {
    private Queue queue;
    private int max = 16;
    private ReentrantLock lock = new ReentrantLock();
    // 队列没有空
    private Condition notEmpty = lock.newCondition();
    // 队列没有满
    private Condition notFull = lock.newCondition();

    public MyBlockingQueueForCondition(int size) {
        this.max = size;
        queue = new LinkedList();
    }

    public void put(Object o) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == max) {
                notFull.await();
            }
            queue.add(o);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            Object item = queue.remove();
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }

}

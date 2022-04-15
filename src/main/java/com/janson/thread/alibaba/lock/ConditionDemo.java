package com.janson.thread.alibaba.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 生产者消费实例，多生产者，多消费者
 * @Author: shanjian
 * @Date: 2022/4/15 10:08 上午
 */
public class ConditionDemo {

    static class Storehouse {

        // 容量
        private int cap;
        private List<Integer> lists = new LinkedList<>();

        // lock和condition准备
        private Lock lock = new ReentrantLock();
        private final Condition addCondition = lock.newCondition();
        private final Condition subCondition = lock.newCondition();

        public Storehouse(int cap) {
            this.cap = cap;
        }

        /**
         * 放入
         */
        public void add(int prod) {
            lock.lock();
            try {
                while (lists.size() == cap) {
                    // 满了。生产线程等待
                    addCondition.await();
                }
                lists.add(prod);
                System.out.println(Thread.currentThread().getName() + " put " + prod + ", The Lists Size is " + lists.size());
                //唤醒消费线程
                subCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * 消费
         */
        public Integer sub() {
            lock.lock();
            try {
                while (lists.size() == 0) {
                    //当集合为空时，当前消费线程等待
                    subCondition.await();
                }

                Integer prod = lists.remove(0);
                System.out.println(Thread.currentThread().getName() + " sub " + prod);
                //唤醒生产线程
                addCondition.signal();
                return prod;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return null;
        }

    }

    public static void main(String[] args) {
        Random random = new Random();
        Storehouse storehouse = new Storehouse(10);
        Runnable addTask = () -> {
            while (true) {
                storehouse.add(random.nextInt(1000));
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable subTask = () -> {
            while (true) {
                storehouse.sub();
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // 3个生产者
        new Thread(addTask).start();
        new Thread(addTask).start();
        new Thread(addTask).start();


        // 3个消费者
        new Thread(subTask).start();
        new Thread(subTask).start();
        new Thread(subTask).start();
    }


}

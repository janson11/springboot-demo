package com.janson.thread.alibaba.communication;

/**
 * @Description: 伪唤醒，不是唤醒有什么问题，而是代码逻辑不严谨，导致被唤醒后的执行不正确。
 * 伪唤醒示例：生产者1消费者2  一个产品两个消费者，出现-1
 * @Author: shanjian
 * @Date: 2022/4/6 11:26 上午
 */
public class SpuriousWakeup {

    private final Object lock = new Object();
    private int product = 0;
    // 如果没有产品，在lock对象上等待唤醒，如果有产品，消费。
    private Runnable consumer = () -> {
        System.out.println(Thread.currentThread().getName() + " prepare consume");
        synchronized (lock) {
            // 替换为while解决线程虚假唤醒问题
            while (product <= 0) {
//            if (product <= 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " wakeup");
            }

            product--;
            System.out.println(Thread.currentThread().getName() + " consumed product:" + product);
            if (product < 0) {
                System.err.println(Thread.currentThread().getName() + " spurious lock happend, product: " + product);
            }
        }
    };

    // 生产一个产品然后唤醒一个在Lock对象上等待的consumer
    private Runnable producer = () -> {
        System.out.println(Thread.currentThread().getName() + " prepare produce");
        synchronized (lock) {
            product += 1;
            System.out.println(Thread.currentThread().getName() + " produced product: " + product);
            lock.notify();
        }
    };

    public void producerAndConsumer() {
        // 启动两个consumer 一个producer
        Thread c1 = new Thread(consumer);
        Thread c2 = new Thread(consumer);
        Thread p = new Thread(producer);
        c1.start();
        c2.start();
        p.start();

    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new SpuriousWakeup().producerAndConsumer();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);

    }
}

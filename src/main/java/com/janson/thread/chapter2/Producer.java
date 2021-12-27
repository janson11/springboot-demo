package com.janson.thread.chapter2;

import java.util.concurrent.BlockingQueue;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/12/27 5:01 下午
 */
public class Producer implements Runnable {
    public volatile boolean canceled = false;
    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 50 == 0) {
                    storage.put(num);
                    System.out.println("producer线程编号：" + Thread.currentThread().getName() + "++" + num + " 是50的倍数，放到仓库中了");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行");
        }
    }
}

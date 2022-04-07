package com.janson.thread.alibaba.startTogether;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/7 11:22 上午
 */
public class StartTogether2CountDownLatchDemo {
    public static void main(String[] args) {
        // 创建计数只有1的倒计数锁存器
        final CountDownLatch cdl = new CountDownLatch(1);

        // 并发数
        int concurrency = 100;

        // 创建倒计数为并发数的N锁存器，用来协同等待N个完成
        final CountDownLatch cdln = new CountDownLatch(concurrency);

        // 用于生成随机等待时长的随机数对象
        final Random rd = new Random();

        for (int i = 0; i < concurrency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 代码进行时间不等的准备工作
                    try {
                        Thread.sleep(rd.nextInt(10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 准备就绪
                    System.out.println(Thread.currentThread().getName() + " 准备就绪");

                    // 调用countDown()报告完成任务（准备就绪）
                    cdln.countDown();

                    // 协同 等待别的线程准备就绪
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 从等待中被释放，继续执行。
                    System.out.println(Thread.currentThread().getName() + " 开始工作。。。");

                }
            }).start();
        }

        // 等待准备完成
        try {
            cdln.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("***************发出开始信号***************");
        // 主线程让计数变成0 来释放等待的线程
        cdl.countDown();

    }
}

package com.janson.thread.alibaba.startTogether;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/7 3:06 下午
 */
public class StartTogetherCyclicBarrierDemo {

    public static void main(String[] args) {
        int concurrency = 100;
        final CyclicBarrier cb = new CyclicBarrier(concurrency, new Runnable() {
            @Override
            public void run() {
                System.out.println("************准备完成************");
            }
        });

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

                    try {
                        cb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                        return;
                    }

                    System.out.println(Thread.currentThread().getName() + " 开始工作");
                }
            }).start();
        }
    }
}

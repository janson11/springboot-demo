package com.janson.thread.alibaba.controlConcurency;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Description: 限流场景 hystric guaua包场景使用
 * @Author: shanjian
 * @Date: 2022/4/8 11:10 上午
 */
public class SemaphoreDemo {

    private Semaphore sp = new Semaphore(10,true);

    private Random rd = new Random();

    public void useCar() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"需要用车!");
        long s = System.currentTimeMillis();
        // 获取许可
        sp.acquire();

        System.out.println(Thread.currentThread().getName() +" 许可用车，等待了："+(System.currentTimeMillis()-s));

        try {
            Thread.sleep(rd.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() +" 用车完毕");
            // 释放许可
            sp.release();
        }
    }

    public static void main(String[] args) {
        final Random rd = new Random();
        final SemaphoreDemo sd = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(rd.nextInt(5000));
                        sd.useCar();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

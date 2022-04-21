package com.janson.thread.alibaba.lock.mylock;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/21 9:07 上午
 */
public class LockSupportDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("111111111111111" + Thread.interrupted());
            LockSupport.park();
            System.out.println("22222222222222222222");
        });


        t1.start();
        Thread.sleep(1000L);

        t1.interrupt();
    }
}

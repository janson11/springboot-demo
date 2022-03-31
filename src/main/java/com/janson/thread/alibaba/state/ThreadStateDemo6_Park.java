package com.janson.thread.alibaba.state;

import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/31 6:13 下午
 */
public class ThreadStateDemo6_Park {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            LockSupport.park();

        });

        t1.start();
        Thread.sleep(2000L);
        System.out.println("t1 被park后的状态："+t1.getState());
        LockSupport.unpark(t1);
        Thread.sleep(1000L);
        System.out.println("t1 被unpark后的状态："+t1.getState());

    }
}

package com.janson.thread.alibaba.lock.mylock;

import io.netty.channel.unix.Errors;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 手写自定义的ReentrantLock
 * @Author: shanjian
 * @Date: 2022/4/20 4:53 下午
 */
public class MyReentrantLock implements Lock {
    /**
     * 锁的基本使用流程
     * 抢到锁，继续执行
     * 没有抢到锁，阻塞等待
     * 释放锁，唤醒一个等待的线程
     */


    /**
     * 不能在代码中使用。
     *
     */
    private static final sun.misc.Unsafe UNSAFE = Unsafe.getUnsafe();

    private static volatile Long STATE;

    static {
        try {
            Class<?> lockClass = MyReentrantLock.class;
            STATE = UNSAFE.objectFieldOffset(lockClass.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }



    private volatile int state;



    @Override
    public void lock() {


    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        /**
         * 需要一个互斥量，用什么来充当互斥量
         * 可否通过一个int属性，CAS操作compareAndSwap(0,1)来充当互斥量，设置成功，抢到锁
         */
        UNSAFE.compareAndSwapInt(this, STATE, 0, 1);
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {


        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

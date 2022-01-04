package com.janson.thread.chapter16;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 支持锁的降级，不支持升级的演示
 * 这段代码会打印出“获取到了读锁”，但是却不会打印出“成功升级”，因为 ReentrantReadWriteLock 不支持读锁升级到写锁。
 * @Author: Janson
 * @Date: 2022/1/4 16:08
 **/
public class ReadWriteLockUpgradeDemo {
    private final static ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        upgrade();
    }

    public static void upgrade() {
        rw1.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "获取到了读锁");
        rw1.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "成功升级");
    }
}

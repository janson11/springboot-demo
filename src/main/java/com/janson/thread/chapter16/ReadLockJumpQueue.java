package com.janson.thread.chapter16;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 显示读锁不插队
 * @Author: Janson
 * @Date: 2022/1/4 15:43
 **/
public class ReadLockJumpQueue {

    private static final ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock READ_LOCK = REENTRANT_READ_WRITE_LOCK.readLock();
    private static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = REENTRANT_READ_WRITE_LOCK.writeLock();

    private static void read() {
        READ_LOCK.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            READ_LOCK.unlock();
        }
    }

    private static void write() {
        WRITE_LOCK.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            WRITE_LOCK.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> read(), "Thread-2").start();
        new Thread(() -> read(), "Thread-4").start();
        new Thread(() -> write(), "Thread-3").start();
        new Thread(() -> write(), "Thread-5").start();

    }

}

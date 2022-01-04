package com.janson.thread.chapter16;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写锁降级功能演示
 * @Author: Janson
 * @Date: 2022/1/4 15:55
 **/
public class CachedData {
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();

    void processCachedData() {
        rw1.readLock().lock();
        if (!cacheValid) {
            // 在获取写锁之前，必须首先释放读锁。
            rw1.readLock().unlock();
            rw1.writeLock().lock();
            try {
                // 这些需要再次判断数据的有效性，因为我们在释放锁和获取写锁的空隙之内，可能有其他线程修改了数据。
                if (!cacheValid) {
                    data = new Object();
                    cacheValid = true;
                }
                // 在不释放写锁的情况下，直接获取读锁，这就是读写锁的降级。
                rw1.readLock().lock();
            } finally {
                // 释放了写锁，但是依然持有读锁
                rw1.writeLock().unlock();
            }
        }
        try {
            System.out.println(data);
        } finally {
            // 释放读锁
            rw1.readLock().unlock();
        }
    }
}

package com.janson.thread.alibaba.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写锁降级示例
 * @Author: shanjian
 * @Date: 2022/4/15 10:48 上午
 */
public class ReadWriteLockDowngradeDemo {

    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void processCachedCache() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // must release read lock before acquiring write lock
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have
                // acquired write lock and changed state before we did.
                if (!cacheValid){
                    data =null;
                    cacheValid =true;
                }
                // 锁降级使用示例
                // Downgrade by acquiring read lock before releasing write lock
                rwl.readLock().lock();
            }finally {
                rwl.writeLock().unlock();
            }
        }

        try {
            use(data);
        } finally {
            rwl.readLock().unlock();
        }
    }

    private void use(Object data) {

    }
}

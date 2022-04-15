package com.janson.thread.alibaba.lock;

import com.janson.mybatis.demo.po.User;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写锁示例
 * @Author: shanjian
 * @Date: 2022/4/15 10:41 上午
 */
public class ReadWriteLockDemo {
    private final Map<String, User> m = new TreeMap<>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public User get(String key) {
        r.lock();
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }


    public Object[] allKeys() {
        r.lock();
        try {
            return m.keySet().toArray();
        } finally {
            r.unlock();
        }
    }

    public User put(String key, User value) {
        w.lock();
        try {
            return m.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public void clear() {
        w.lock();
        try {
            m.clear();
        } finally {
            w.unlock();
        }
    }

}

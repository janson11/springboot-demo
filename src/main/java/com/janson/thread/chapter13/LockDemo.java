package com.janson.thread.chapter13;

import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/4 11:29
 **/
public class LockDemo {

    public void tryLock(Lock lock1, Lock lock2) throws InterruptedException {
        while (true) {
            if (lock1.tryLock()) {
                try {
                    if (lock2.tryLock()) {
                        try {
                            System.out.println("获取到了两把锁，完成业务逻辑");
                            return;
                        } finally {
                            lock2.unlock();
                        }
                    }
                } finally {
                    lock1.unlock();
                }
            } else {
                Thread.sleep(new Random().nextInt());
            }
        }
    }


    public void lockInterruptibly(Lock lock) {
        try {
            lock.lockInterruptibly();
            try {
                System.out.println("操作资源");
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

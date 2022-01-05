package com.janson.thread.chapter17;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 实现一个可重入的自旋锁
 * @Author: Janson
 * @Date: 2022/1/4 17:52
 **/
public class ReentranSpinLock {
    private AtomicReference<Thread> owner = new AtomicReference<>();
    // 重入次数
    private int count = 0;

    public void lock() {
        Thread t = Thread.currentThread();
        if (t == owner.get()) {
            ++count;
            return;
        }
        // 自旋获取锁
        while (!owner.compareAndSet(null, t)) {
            System.out.println("自旋了");
        }
    }

    public void unlock() {
        Thread t = Thread.currentThread();
        // 只有持有锁的线程才能解锁
        if (t == owner.get()) {
            if (count > 0) {
                --count;
            } else {
                //此处无须CAS操作，因为没有竞争，因为只有线程持有者才能解锁
                owner.set(null);
            }
        }
    }

    public static void main(String[] args) {
        ReentranSpinLock spinLock = new ReentranSpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                spinLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了自旋锁");
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}

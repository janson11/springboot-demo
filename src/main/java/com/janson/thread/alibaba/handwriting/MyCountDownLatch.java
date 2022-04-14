package com.janson.thread.alibaba.handwriting;

import org.springframework.ui.context.Theme;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/14 9:14 上午
 */
public class MyCountDownLatch {

    private AtomicInteger count;
    private List<Thread> waiters;

    public MyCountDownLatch(int count) {
        this.count = new AtomicInteger(count);
        this.waiters = Collections.synchronizedList(new LinkedList<>());
    }

    public void countDown() {
        for (;;){
            int c = count.get();
            if (c<=0) {
                break;
            }
            int nextC = c-1;
            if (count.compareAndSet(c,nextC)) {
                if (nextC==0) {
                    doRelease();
                }
                break;
            }
        }
    }

    private void doRelease() {
        waiters.forEach( thread -> LockSupport.unpark(thread));
        // help gc
        waiters.clear();
    }

    public void await() throws InterruptedException {
        int c = count.get();
        if (c<=0) {
            return;
        }
        // 本地线程加入等待队列
        addWaiter();
        //本地线程等待，注意for循环的使用
        for (;;) {
            c = count.get();
            if (c<=0) {
                return;
            }
            LockSupport.park(this);
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
        }

    }

    private void addWaiter() {
        waiters.add(Thread.currentThread());
    }

    public static void main(String[] args) {
        MyCountDownLatch myCountDownLatch = new MyCountDownLatch(3);
        Thread t1 = new Thread( () ->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 执行 countDown() at "+System.currentTimeMillis()/1000);
            myCountDownLatch.countDown();
        });

        Thread t2 = new Thread( () ->{
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 执行 countDown() at "+System.currentTimeMillis()/1000);
            myCountDownLatch.countDown();
        });

        Thread t3 = new Thread( () ->{
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3 执行 countDown() at "+System.currentTimeMillis()/1000);
            myCountDownLatch.countDown();
        });

        t1.start();
        t2.start();
        t3.start();

        System.out.println("main 执行await() at "+System.currentTimeMillis()/1000);
        try {
            myCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main 从await() 醒来 at "+System.currentTimeMillis()/1000);

    }
}

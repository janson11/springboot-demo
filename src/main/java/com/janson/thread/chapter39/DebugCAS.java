package com.janson.thread.chapter39;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/15 10:09
 **/
public class DebugCAS implements Runnable {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
            System.out.println("线程" + Thread.currentThread().getName() + "执行成功");
        }
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        DebugCAS r = new DebugCAS();
        r.value = 100;
        Thread thread1 = new Thread(r, "Thread 1");
        Thread thread2 = new Thread(r, "Thread-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.value);
    }

    @Override
    public void run() {
        compareAndSwap(100, 150);
    }
}

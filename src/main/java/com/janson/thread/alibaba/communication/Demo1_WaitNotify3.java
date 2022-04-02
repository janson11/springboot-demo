package com.janson.thread.alibaba.communication;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/2 5:26 下午
 */
public class Demo1_WaitNotify3 {
    static int limit = 20;
    static int i = 0;
    static Object obj = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj) {
                while (i < limit) {
                    if (i % 3 == 0) {
                        System.out.println("t1: " + (++i));
                    }
                    obj.notifyAll();

                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                obj.notifyAll();
            }
        }).start();

        new Thread(() -> {
            synchronized (obj) {
                while (i < limit) {
                    if (i % 3 == 1) {
                        System.out.println("t2: " + (++i));
                    }
                    obj.notifyAll();

                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                obj.notifyAll();
            }
        }).start();

        new Thread(() -> {
            synchronized (obj) {
                while (i < limit) {
                    if (i % 3 == 2) {
                        System.out.println("t3: " + (++i));
                    }
                    obj.notifyAll();

                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                obj.notifyAll();
            }
        }).start();
    }
}

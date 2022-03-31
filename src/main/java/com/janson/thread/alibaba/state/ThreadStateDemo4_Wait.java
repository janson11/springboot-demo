package com.janson.thread.alibaba.state;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/31 4:56 下午
 */
public class ThreadStateDemo4_Wait {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (object) {
                try {
                    System.out.println("t1将 wait（3000L）");
                    object.wait(3000L);
                    System.out.println("t1将 wait");
                    object.wait();
                    System.out.println("t1 执行完");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();

        Thread.sleep(1000L);
        synchronized (object)  {
            System.out.println("t1的状态 0 ："+t1.getState());
            object.notify();
            Thread.sleep(1000L);
            System.out.println("t1的状态 1 ："+t1.getState());
        }

        Thread.sleep(3000);
        System.out.println("t1的状态 2 ："+t1.getState());

        Thread.sleep(1000);
        synchronized (object) {
            object.notify();
        }

        System.out.println("t1的状态 3："+t1.getState());
        Thread.sleep(1000);
        System.out.println("t1的状态 4 ："+t1.getState());

    }
}

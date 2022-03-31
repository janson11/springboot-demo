package com.janson.thread.alibaba.state;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/31 4:48 下午
 */
public class ThreadStateDemo3_Synchronized {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (ThreadStateDemo3_Synchronized.class) {
                System.out.println("t1 抢到锁");
            }
        });

        synchronized (ThreadStateDemo3_Synchronized.class) {
            t1.start();
            Thread.sleep(1000L);
            System.out.println("t1 抢不到锁时的状态：" + t1.getState());
        }
    }
}

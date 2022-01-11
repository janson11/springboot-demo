package com.janson.thread.chapter23;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/6 21:50
 **/
public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    static Score math;
    static Score computer;

    public static AtomicIntegerFieldUpdater<Score> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Score.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            computer.score++;
            scoreUpdater.getAndIncrement(math);
        }
    }

    public static class Score {
        volatile int score;
    }


    public static void main(String[] args) throws InterruptedException {
        math = new Score();
        computer = new Score();
        AtomicIntegerFieldUpdaterDemo atomicIntegerFieldUpdaterDemo = new AtomicIntegerFieldUpdaterDemo();
        Thread t1 = new Thread(atomicIntegerFieldUpdaterDemo);
        Thread t2 = new Thread(atomicIntegerFieldUpdaterDemo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("普通变量的结果：" + computer.score);
        System.out.println("升级后变量的结果：" + math.score);

    }
}

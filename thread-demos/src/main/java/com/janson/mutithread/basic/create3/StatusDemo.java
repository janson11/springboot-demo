package com.janson.mutithread.basic.create3;

import com.janson.util.Print;

import java.util.ArrayList;
import java.util.List;

import static com.janson.util.ThreadUtil.sleepMilliSeconds;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/3/16 20:59
 **/
public class StatusDemo {

    // 每个线程池执行的轮次
    public static final long MAX_TURN = 5;

    // 线程编号
    static int threadSeqNumber = 0;

    // 全局的静态线程列表
    static List<Thread> threadList = new ArrayList<Thread>();

    // 输出静态线程列表中，所有线程的状态
    private static void printThreadStatus() {
        for (Thread thread : threadList) {
            Print.cfo(thread.getName() + " 状态为 " + thread.getState());
        }
    }


    // 向全局的静态线程列表加入线程
    private static void addStatusThread(Thread thread) {
        threadList.add(thread);
    }

    static class StatusDemoThread extends Thread {
        public StatusDemoThread() {
            super("statusPrintThread" + (++threadSeqNumber));
            //将自己加入到全局的静态线程列表
            addStatusThread(this);
        }

        @Override
        public void run() {
            Print.tco(getName() + ",状态为" + getState());
            for (int i = 0; i < MAX_TURN; i++) {
                // 线程睡眠
                sleepMilliSeconds(500);
                // 输出所有线程的状态
                printThreadStatus();
            }

            Print.tco(getName() + "-运行结束.");
        }
    }

    public static void main(String[] args) {
        addStatusThread(Thread.currentThread());

        Thread sThread1 = new StatusDemoThread();
        Print.cfo(sThread1.getName() + "-状态为" + sThread1.getState());
        Thread sThread2 = new StatusDemoThread();
        Print.cfo(sThread2.getName() + "-状态为" + sThread2.getState());
        Thread sThread3 = new StatusDemoThread();
        Print.cfo(sThread3.getName() + "-状态为" + sThread3.getState());

        sThread1.start();

        sleepMilliSeconds(500);// 等待500ms启动第二个线程
        sThread2.start();

        sleepMilliSeconds(1000);// 等待1000ms启动第三个线程
        sThread3.start();

        sleepMilliSeconds(100);// 睡眠100秒

    }

}

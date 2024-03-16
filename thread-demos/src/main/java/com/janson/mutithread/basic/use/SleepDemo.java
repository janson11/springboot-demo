package com.janson.mutithread.basic.use;

import com.janson.util.Print;
import static com.janson.util.ThreadUtil.getCurThreadName;
/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/3/16 21:50
 **/
public class SleepDemo {

    public static final int SLEEP_GAP = 5000;//睡眠时长

    public static final int MAX_TRUN = 50;//睡眠次数

    static class SleepThread extends Thread {

        static int threadSeqNumber = 1;

        public SleepThread() {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < MAX_TRUN; i++) {
                    Print.tco(getName() + ",睡眠轮次：" + i);
                    // 线程睡眠一会
                    Thread.sleep(SLEEP_GAP);
                }
            } catch (InterruptedException e) {
                Print.tco(getName() + " 发生一次被中断.");
            }
            Print.tco(getName() + "运行结束.");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new SleepThread();
            thread.start();
        }
        Print.tco(getCurThreadName()+" 运行结束.");
    }
}

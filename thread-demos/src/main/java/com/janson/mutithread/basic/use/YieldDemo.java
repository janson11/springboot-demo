package com.janson.mutithread.basic.use;

import com.janson.util.Print;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.janson.util.ThreadUtil.sleepSeconds;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/3/20 23:19
 **/
public class YieldDemo {

    public static final int MAX_TURN = 100;// 执行次数

    public static AtomicInteger index = new AtomicInteger(0);// 执行编号

    //记录线程的执行次数
    private static Map<String, AtomicInteger> metric = new HashMap<String, AtomicInteger>();

    // 输出线程的执行次数
    private static void printMetric() {
        Print.tco("metric= " + metric);
    }


    static class YieldThread extends Thread {
        static int threadNumber = 1;

        public YieldThread() {
            super("YieldThread-" + threadNumber);
            threadNumber++;
            metric.put(this.getName(), new AtomicInteger(0));
        }

        @Override
        public void run() {
            for (int i = 1; i < MAX_TURN && index.get() < MAX_TURN; i++) {
                Print.tco("线程优先级：" + getPriority());
                index.incrementAndGet();
                metric.get(this.getName()).incrementAndGet();
                if (i % 2 == 0) {
                    //让步，出让执行的权限
                    Thread.yield();
//                    Print.tco(this.getName() + "出让执行的权限 ,i:" + i);

                }
            }
            // 输出线程的执行次数
            printMetric();
            Print.tco(this.getName() + " 运行结束.");
        }
    }


    @Test
    public void test1() {
        Thread thread1 = new YieldThread();
        thread1.setPriority(Thread.MAX_PRIORITY);
        Thread thread2 = new YieldThread();
        thread2.setPriority(Thread.MIN_PRIORITY);
        Print.tco("启动线程.");
        thread1.start();
        thread2.start();
        sleepSeconds(100);
    }
}

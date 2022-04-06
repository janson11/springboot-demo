package com.janson.thread.alibaba.waitNCondition;

import org.checkerframework.checker.units.qual.C;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/6 5:08 下午
 */
public class MultipleConditionOkCountDownLatchDemo {

    static class InitWorker implements Runnable {
        // 任务编号
        int taskNo;
        // 要初始化的数组
        int[] arr;
        // 负责初始化的范围
        int startIndex, endIndex;
        // 生成随机整数的范围
        int bound;

        CountDownLatch cdl;

        public InitWorker(int taskNo, int[] arr, int startIndex, int endIndex, int bound,CountDownLatch cdl) {
            this.taskNo = taskNo;
            this.arr = arr;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.bound = bound;
            this.cdl = cdl;
        }

        @Override
        public void run() {
            Random rd = new Random();
            for (int i = startIndex; i < endIndex; i++) {
                arr[i] = rd.nextInt(bound);
            }
            System.out.println("任务-" + taskNo + " 完成");
            cdl.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 数组大小
        int size = 10_000_000;
        final int[] iarray = new int[size];
        // 随机数范围
        int bound = 10_000;
        // 分段大小
        int segmentSize = 100_000;
        // 分成的任务个数
        int jobCount = size / segmentSize;

        ExecutorService tpool = Executors.newFixedThreadPool(6);
        CountDownLatch cdl = new CountDownLatch(jobCount);

        for (int i = 0; i < jobCount; i++) {
            tpool.execute(new InitWorker(i, iarray, i * segmentSize, (i + 1) * segmentSize - 1, bound,cdl));
        }
        System.out.println("任务分好了，等待完成。。。。");

        long s1 =System.currentTimeMillis();
        cdl.await();
        System.out.println("任务都完成了,等待时间"+ (System.currentTimeMillis()-s1)+"，继续后续工作。。。");

        tpool.shutdown(); 


    }

}

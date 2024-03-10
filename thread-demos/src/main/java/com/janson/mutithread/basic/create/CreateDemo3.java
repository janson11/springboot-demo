package com.janson.mutithread.basic.create;

import com.janson.util.Print;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static com.janson.util.ThreadUtil.getCurThreadName;

/*
 * @Description:
 * @Author: Janson
 * @Date: 2024/3/8 7:26
 **/
public class CreateDemo3 {

    public static final int MAX_TURN = 5;

    public static final int COMPUTE_TIMES = 100000000;

    static class ReturnableTask implements Callable<Long> {
        // 返回并发执行的时间
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            Print.cfo(getCurThreadName() + " 线程运行开始");
            Thread.sleep(1000);
            for (int i = 0; i < COMPUTE_TIMES; i++) {
                int j = i * 10000;
            }
            long used = System.currentTimeMillis() - startTime;
            Print.cfo(getCurThreadName() + " 线程运行结束");
            return used;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReturnableTask task = new ReturnableTask();
        FutureTask<Long> futureTask = new FutureTask<Long>(task);
        Thread thread = new Thread(futureTask, "returnableThread");
        thread.start();

        Thread.sleep(500);
        Print.cfo(getCurThreadName() + " 让子弹飞一会儿.");

        Print.cfo(getCurThreadName() + " 做一点自己的事情.");
        for (int i = 0; i < COMPUTE_TIMES; i++) {
            int j = i * 10000;
        }
        Print.cfo(getCurThreadName() + " 获取并发任务的执行结果.");

        try {
            Print.cfo(thread.getName() +"线程占用时间："+futureTask.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        Print.cfo(getCurThreadName() +" 运行结束。");
    }

}


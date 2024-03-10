package com.janson.mutithread.basic.create;

import com.janson.util.Print;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.janson.util.ThreadUtil.getCurThreadName;
import static com.janson.util.ThreadUtil.sleepMilliSeconds;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/3/10 21:51
 **/
public class CreateDemo4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个包含三个线程的线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        //执行线程实例
        pool.execute(new DemoThread());
        // 执行Runable执行目标实例
        pool.execute(new Runnable() {

            @Override
            public void run() {
                for (int j = 1; j < MAX_TURN; j++) {
                    Print.cfo(getCurThreadName() + " ,轮次：" + j);
                    sleepMilliSeconds(10);
                }
            }
        });

       // 提交Callable执行目标实例
        Future<Long> future = pool.submit(new ReturnableTask());
        Long result = future.get();
        Print.cfo("异步任务的执行结果为："+result);
        sleepMilliSeconds(Integer.MAX_VALUE);


    }

    public static final int MAX_TURN = 5;

    static class DemoThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Print.cfo(getCurThreadName() + " ,轮次：" + i);
                sleepMilliSeconds(10);
            }
        }
    }

    static class ReturnableTask implements Callable<Long> {
        //返回并发执行的时间
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            Print.cfo(getCurThreadName() + " 线程运行开始.");
            for (int j = 1; j < MAX_TURN; j++) {
                Print.cfo(getCurThreadName() + ",轮次：" + j);
                sleepMilliSeconds(10);
            }
            long used = System.currentTimeMillis() - startTime;
            Print.cfo(getCurThreadName() + "线程运行结束.");
            return used;
        }
    }

}

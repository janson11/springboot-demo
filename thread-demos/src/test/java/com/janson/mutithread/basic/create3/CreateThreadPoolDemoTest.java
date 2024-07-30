package com.janson.mutithread.basic.create3;

import com.janson.util.Print;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.janson.util.ThreadUtil.sleepSeconds;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/7/23 9:26
 **/
public class CreateThreadPoolDemoTest {


    @Test
    public void testSingleThreadExecutor() {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 5; i++) {
            pool.execute(new CreateThreadPoolDemo.TargetTask());
            pool.submit(new CreateThreadPoolDemo.TargetTask());
        }
        sleepSeconds(100);
        // 关闭线程池
        pool.shutdown();
    }


    @Test
    public void testNewFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            pool.execute(new CreateThreadPoolDemo.TargetTask());
            pool.submit(new CreateThreadPoolDemo.TargetTask());
        }
        sleepSeconds(30);
        // 关闭线程池
        pool.shutdown();
    }

    /**
     * 可缓存线程池
     */
    @Test
    public void testCachedThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.execute(new CreateThreadPoolDemo.TargetTask());
            pool.submit(new CreateThreadPoolDemo.TargetTask());
        }
        sleepSeconds(30);
        // 关闭线程池
        pool.shutdown();
    }

    /**
     * 可调度线程池
     */
    @Test
    public void testNewScheduledThreadPool() {
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 2; i++) {
            scheduled.scheduleAtFixedRate(new CreateThreadPoolDemo.TargetTask(), 0, 500, TimeUnit.MICROSECONDS);
            //以上的参数中：
            // 0表示首次执行任务的延迟时间，500表示每次执行任务的间隔时间
            //TimeUnit.MILLISECONDS所设置的时间的计时单位为毫秒
        }
        sleepSeconds(1000);
        scheduled.shutdown();
    }

    @Test
    public void testThreadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 100, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));
        for (int i = 0; i < 5; i++) {
            final int taskIndex = i;
            executor.execute(() -> {
                Print.tco("taskIndex = " + taskIndex);
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        }
        while (true) {
            Print.tco("- activeCount: " + executor.getActiveCount() + "- taskCount: " + executor.getTaskCount());
            sleepSeconds(1);
        }
    }

    // 一个简单的线程工厂
    static public class SimpleThreadFactory implements ThreadFactory {

        static AtomicInteger threadNo = new AtomicInteger(1);

        //实现其唯一的创建线程方法
        @Override
        public Thread newThread(Runnable r) {
            String threadName = "simpleThread-" + threadNo.get();
            Print.tco("创建一个线程池，名称为:" + threadName);
            threadNo.incrementAndGet();
            // 设置线程的名称
            Thread t = new Thread(r, threadName);
            // 设置为守护线程
            t.setDaemon(true);
            return t;

        }
    }
}
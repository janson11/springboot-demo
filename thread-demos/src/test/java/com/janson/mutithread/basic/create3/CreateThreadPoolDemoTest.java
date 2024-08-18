package com.janson.mutithread.basic.create3;

import com.janson.util.Print;
import com.janson.util.RandomUtil;
import javafx.scene.control.Tab;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
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

    @Test
    public void testThreadFactory() {
        // 使用自定义线程工厂，快捷创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(2, new SimpleThreadFactory());
        for (int i = 0; i < 5; i++) {
            pool.submit(new CreateThreadPoolDemo.TargetTask());
        }
        // 等待10秒后关闭线程池
        sleepSeconds(10);
        Print.tco("等待10秒后关闭线程池");
        pool.shutdown();
    }

    //自定义拒绝策略
    public static class CustomIgnorePolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 打印日志
            Print.tco(r + " rejected; " + " - getTaskCount: " + executor.getTaskCount());
        }
    }


    @Test
    public void testCustomIgnorePolicy() {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        // 最大排队任务数
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        // 线程工厂
        ThreadFactory threadFactory = new SimpleThreadFactory();
        // 拒绝和异常策略
        RejectedExecutionHandler policy = new CustomIgnorePolicy();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, policy);
        // 预启动所有核心线程
        for (int i = 0; i < 10; i++) {
            pool.execute(new CreateThreadPoolDemo.TargetTask());
        }

        // 等待10秒后关闭线程池
        sleepSeconds(10);
        Print.tco("等待10秒后关闭线程池");
        pool.shutdown();
    }


    // 线程本地变量，用于记录线程异步任务的开始执行时间
    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    @Test
    public void testHooks() {
        ExecutorService pool = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2)) {
            @Override
            protected void terminated() {
                Print.tco("调度器已经终止!");
            }

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                Print.tco(r + "前钩子被执行");
                // 记录开始执行时间
                START_TIME.set(System.currentTimeMillis());
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                // 计算执行时长
                long time = (System.currentTimeMillis()) - START_TIME.get();
                Print.tco(r + "后钩子被执行，执行时长：(ms)" + time);
                // 清空本地变量
                START_TIME.remove();
            }
        };

        pool.execute(new CreateThreadPoolDemo.TargetTask());
        // 等待10秒后关闭线程池
        sleepSeconds(10);
        Print.tco("等待10秒后关闭线程池");
        pool.shutdown();
    }


    @Test
    public void testNewFixedThreadPool2() {
        // 创建一个固定大小的线程池
        ExecutorService pool = Executors.newFixedThreadPool(1);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) pool;
        Print.tco(threadPoolExecutor.getMaximumPoolSize());
        // 设置核心线程数
        threadPoolExecutor.setCorePoolSize(8);

        // 创建一个单线程池化的线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        // 转换成普通线程池，惠抛出运行时异常 java.lang.ClassCastException
        ((ThreadPoolExecutor) singleThreadExecutor).setCorePoolSize(8);
    }

    static class TargetTaskWithError extends CreateThreadPoolDemo.TargetTask {
        @Override
        public void run() {
            super.run();
            throw new RuntimeException("Error from " + taskName);
        }
    }

    @Test
    public void testSubmit() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.execute(new TargetTaskWithError());
        /**
         * submit(Runnable task)方法：
         * 该方法用于提交一个 Runnable 任务到线程池中，并返回一个 Future 类型的对象，该对象代表了该 Runnable 任务的执行结果。
         */
        Future<?> future = pool.submit(new TargetTaskWithError());

        try {
            // 如果异常抛出，会在调用Future.get()方法时传递给调用者
            if (future.get() == null) {
                // 如果Future.get()方法返回null，则代表任务执行完成
                Print.tco("任务执行成功");
            }
        } catch (Exception e) {
            Print.tco(e.getCause().getMessage());
        }

        sleepSeconds(10);
        pool.shutdown();
    }

    //测试用例：获取异步调用的结果
    @Test
    public void testSubmit2() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        Future<Integer> future = pool.schedule(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // 返回200 - 300之间的随机数
                return RandomUtil.randInRange(200, 300);
            }
        }, 100, TimeUnit.MILLISECONDS);

        try {
            Integer result = future.get();
            Print.tco("异步调用的结果：" + result);
        } catch (InterruptedException e) {
            Print.tco("异步调用被中断");
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            Print.tco("异步调用过程中，发生了异常");
            throw new RuntimeException(e);
        }

        sleepSeconds(10);
        pool.shutdown();
    }


    @Test
    public void testShutdownGracefully() {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
        threadPool.shutdown();//Disable new tasks from being submitted
        try {
            // 设定最大重试次数
            // 等待 60s
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                // 调用shutdownNow()方法，取消正在执行的任务
                // 再次等待60s,如果还未结束，可以再次尝试或直接放弃
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("线程池任务未正常执行结束");
                }
            }
        } catch (InterruptedException e) {
            threadPool.shutdown();
        }
    }

}
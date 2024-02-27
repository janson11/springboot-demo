package com.janson.util;

import java.lang.annotation.Target;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 线程工具类
 * @Author: Janson
 * @Date: 2024/2/27 9:15
 **/
public class ThreadUtil {

    /**
     * CPU核数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * 定制的线程工厂
     */
    private static class CustomThreadFactory implements ThreadFactory {

        // 线程池数量
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;

        // 线程数量
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String threadTag;


        CustomThreadFactory(String threadTag) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.threadTag = "appoool-" + poolNumber.getAndIncrement() + "-" + threadTag + "-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, threadTag + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    /**
     * 空闲保活时限，单位秒
     */
    private static final int KEEP_ALIVE_SECONDS = 30;

    /**
     * 有界队列size
     */
    private static final int QUEUE_SIZE = 10000;

    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 0;

    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT;

    // 懒汉式单例创建线程池，用于CPU密集型任务
    private static class CpuIntenseTargetThreadPoolLazyHolder {
        // 线程池 ：用于CPU密集型任务
        private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
                MAXIMUM_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_SECONDS,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_SIZE),
                new CustomThreadFactory("cpu")
        );


    }
}

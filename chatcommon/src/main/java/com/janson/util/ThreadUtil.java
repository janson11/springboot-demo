package com.janson.util;

import javax.validation.constraints.Null;
import java.lang.annotation.Target;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

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
        private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(MAXIMUM_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE), new CustomThreadFactory("cpu"));

        static {
            EXECUTOR.allowCoreThreadTimeOut(true);
            //JVM 关闭时的钩子函数
            Runtime.getRuntime().addShutdownHook(new ShutDownHookThread("CPU密集型任务线程池", new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    // 优雅关闭线程池
                    shutdownThreadPoolGracefully(EXECUTOR);
                    return null;
                }
            }));
        }
    }


    /**
     * 获取可调度线程池（包含提交延迟、定时、周期性、顺序性执行的任务）
     *
     * @return
     */
    public static ScheduledThreadPoolExecutor getSeqOrScheduledExecutorService() {
        return SeqOrScheduledTargetThreadPoolLazyHolder.EXECUTOR;
    }

    public static void seqExecute(Runnable command) {
        getSeqOrScheduledExecutorService().execute(command);
    }


    /**
     * 延迟执行
     *
     * @param command
     * @param i
     * @param unit
     */
    public static void delayRun(Runnable command, int i, TimeUnit unit) {
        getSeqOrScheduledExecutorService().schedule(command, i, unit)
    }


    /**
     * 固定频率执行
     *
     * @param command
     * @param i
     * @param unit
     */
    public static void scheduleAtFixedRate(Runnable command, int i, TimeUnit unit) {
        getSeqOrScheduledExecutorService().scheduleAtFixedRate(command, i, i, unit);
    }

    /**
     * 线程休眠
     *
     * @param second 秒
     */
    public static void sleepSeconds(int second) {
        LockSupport.parkNanos(second * 1000L * 1000L * 1000L);
    }

    /**
     * 线程休眠
     *
     * @param millisecond 毫秒
     */
    public static void sleepMilliSeconds(int millisecond) {
        LockSupport.parkNanos(millisecond * 1000L * 1000L);
    }


    /**
     * 获取当前线程名称
     *
     * @return
     */
    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * 获取当前线程ID
     *
     * @return
     */
    public static long getCurThreadId() {
        return Thread.currentThread().getId();
    }

    /**
     * 获取当前线程
     *
     * @return
     */
    public static Thread getCurThread() {
        return Thread.currentThread();
    }


    /**
     * 调用栈中的类名
     *
     * @param level
     * @return
     */
    public static String stackClassName(int level) {
//        Thread.currentThread().getStackTrace()[1]是当前方法 curClassName 执行堆栈
//        Thread.currentThread().getStackTrace()[2]就是 curClassName 的 上一级的方法堆栈 以此类推

        //调用的类名
        String className = Thread.currentThread().getStackTrace()[level].getClassName();
        return className;
    }


    /**
     * 调用栈中的方法名称
     *
     * @param level
     * @return
     */
    public static String stackMethodName(int level) {
//        Thread.currentThread().getStackTrace()[1]是当前方法 curClassName 执行堆栈
//        Thread.currentThread().getStackTrace()[2]就是 curClassName 的 上一级的方法堆栈 以此类推

        //调用的类名
        String methodName = Thread.currentThread().getStackTrace()[level].getMethodName();
        return methodName;
    }

    //懒汉式单例创建线程池：用于定时任务、顺序排队执行任务
    static class SeqOrScheduledTargetThreadPoolLazyHolder {
        static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(1, new CustomThreadFactory("seq"));

        static {
            //JVM 关闭时的钩子函数
            Runtime.getRuntime().addShutdownHook(new ShutDownHookThread("定时和顺序任务线程池", new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    // 优雅关闭线程池
                    shutdownThreadPoolGracefully(EXECUTOR);
                    return null;
                }
            }));
        }


    }

    public static void shutdownThreadPoolGracefully(ExecutorService threadPool) {
        if (!(threadPool instanceof ExecutorService) || threadPool.isTerminated()) {
            return;
        }
        try {
            threadPool.shutdown();// 拒绝接受新任务
        } catch (SecurityException e) {
            return;
        } catch (NullPointerException e) {
            return;
        }

        try {
            // 等待60s 等待线程池中的任务完成执行
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                // 调用shutdownNow 取消正在执行的任务
                threadPool.shutdownNow();
                // 再次等待60s 如果还未结束，可以再次尝试，或则直接放弃
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("线程池任务未正常执行结束");
                }
            }

        } catch (InterruptedException e) {
            // 捕获异常，重新调用shutdownNow
            threadPool.shutdownNow();
        }

        // 依然灭有关闭，循环关闭100次，每次等等10毫秒
        if (!threadPool.isTerminated()) {
            try {
                for (int i = 0; i < 1000; i++) {
                    if (threadPool.awaitTermination(10, TimeUnit.MICROSECONDS)) {
                        break;
                    }
                    threadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            } catch (Throwable e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

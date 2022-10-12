package com.janson.netty.concurrent;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/11 9:55 上午
 */
@Slf4j
public class GuavaFutureDemo {

    public static final int SLEEP_GAP = 5000;

    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean> {

        @Override
        public Boolean call() {
            try {
                log.info("洗好水壶");
                log.info("灌上凉水");
                log.info("放在火上");

                // 线程睡眠一段时间，代表烧水中
                Thread.sleep(SLEEP_GAP);
                log.info("水开了");
            } catch (InterruptedException e) {
                log.error("发生异常被中断", e);
                return false;
            }
            log.info("{}运行结束", getCurThreadName());
            return true;
        }
    }

    static class WashJob implements Callable<Boolean> {

        @Override
        public Boolean call() {
            try {
                log.info("洗茶壶");
                log.info("洗茶杯");
                log.info("拿茶叶");

                // 线程睡眠一段时间，代表清洗中
                Thread.sleep(SLEEP_GAP);
                log.info("洗完了");
            } catch (InterruptedException e) {
                log.error("发生异常被中断", e);
                return false;
            }
            log.info("{}运行结束", getCurThreadName());
            return true;
        }
    }


    // 泡茶线程
    static class MainJob implements Runnable {

        volatile boolean waterOk = false;
        volatile boolean cupOk = false;
        int gap = SLEEP_GAP / 10;


        @Override
        public void run() {
            while (true) {
                try {
                    log.info("读书中.......");
                    Thread.sleep(gap);
                } catch (InterruptedException e) {
                    log.info("{}发送异常被中断", getCurThreadName());
                }
            }

        }


        public void drinkTea() {
            if (waterOk && cupOk) {
                log.info("泡茶喝,茶喝完");
                this.waterOk = false;
                this.gap = SLEEP_GAP * 100;
            } else if (!waterOk) {
                log.info("烧水失败，没有茶喝了");
            } else if (!cupOk) {
                log.info("杯子洗不了,没有茶喝了");
            }
        }

    }


    public static void main(String[] args) {
        // 新起一个线程，作为泡茶主线程
        MainJob mainJob = new MainJob();
        Thread mainThread = new Thread(mainJob);
        mainThread.setName("喝茶线程");
        mainThread.start();


        // 烧水的业务逻辑
        Callable<Boolean> hJob = new HotWaterJob();

        Callable<Boolean> wJob = new WashJob();

        // 创建java线程池
        ExecutorService jPool = Executors.newFixedThreadPool(10);

        //包装java线程池，构造guava线程池
        ListeningExecutorService gPool = MoreExecutors.listeningDecorator(jPool);

        //提交烧水的业务逻辑，取到异步任务
        ListenableFuture<Boolean> hotFuture = gPool.submit(hJob);

        // 绑定任务执行完成后的回调，到异步任务
        Futures.addCallback(hotFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                log.info("烧水成功，尝试喝茶");
                if (result) {
                    mainJob.waterOk =true;
                    mainJob.drinkTea();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                log.info("烧水失败，没有茶喝了");

            }
        });


        // 提交清洗的业务逻辑，取到异步任务
        ListenableFuture<Boolean> washFuture = gPool.submit(wJob);
        // 绑定任务执行完成后的回调，到异步任务
        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                log.info("杯子洗成功，尝试喝茶");
                if (result) {
                    mainJob.cupOk = true;
                    mainJob.drinkTea();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                log.info("杯子洗不了，没有茶喝了");
            }
        });


        log.info("{}运行结束", getCurThreadName());
    }
}

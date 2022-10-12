package com.janson.netty.concurrent;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;


/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/11 9:55 上午
 */
@Slf4j
public class NettyFutureDemo {

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

        // 创建netty线程池
        DefaultEventExecutorGroup nPool = new DefaultEventExecutorGroup(2);
        Future<Boolean> hotFuture = nPool.submit(hJob);

        // 绑定任务执行完成后的回调，到异步任务
        hotFuture.addListener(new GenericFutureListener<Future<? super Boolean>>() {
            @Override
            public void operationComplete(Future<? super Boolean> future) throws Exception {
                if (future.isSuccess()) {
                    mainJob.waterOk = true;
                    log.info("烧水完成，尝试着去超好吃吃茶");
                    mainJob.drinkTea();
                } else {
                    mainJob.waterOk = false;
                    log.info("烧水失败啦！");
                }
            }
        });


        // 提交清洗的业务逻辑，取到异步任务
        Future<Boolean> washFuture = nPool.submit(wJob);
        // 绑定任务执行完成后的回调，到异步任务
        washFuture.addListener(new GenericFutureListener<Future<? super Boolean>>() {
            @Override
            public void operationComplete(Future<? super Boolean> future) throws Exception {
                if (future.isSuccess()) {
                    mainJob.cupOk = true;
                    log.info("杯子洗成功，尝试喝茶");
                    mainJob.drinkTea();
                } else {
                    mainJob.cupOk = false;
                    log.info("杯子洗不了，没有茶喝了");
                }
            }
        });

        log.info("{}运行结束", getCurThreadName());
    }
}

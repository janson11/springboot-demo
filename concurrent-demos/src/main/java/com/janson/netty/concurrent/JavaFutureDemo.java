package com.janson.netty.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: javaFuture
 *  FutureTask类的实现比join线程合并操作更加高明，能取得异步线程的结果。但是也就未必高明到哪里去了。为啥呢
 *  因为通过FutureTask类的get方法，获取异步结果时，主线程也会被阻塞的。这一点，FutureTask和join也是一样的，
 *  他们都是异步阻塞模式。
 *
 *  如果需要获取异步的结果，则需要引入一些额外的框架，比如Guava框架
 * @Author: shanjian
 * @Date: 2022/10/11 9:55 上午
 */
@Slf4j
public class JavaFutureDemo {

    public static final int SLEEP_GAP = 500;

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


    public static void drinkTea(boolean waterOk,boolean cupOk) {
        if (waterOk && cupOk) {
            log.info("泡茶喝");
        } else if (!waterOk) {
            log.info("烧水失败，没有茶喝了");
        } else if (!cupOk) {
            log.info("被子洗不了,没有茶喝了");
        }
    }

    public static void main(String[] args) {
        // 异步逻辑
        Callable<Boolean> hJob = new HotWaterJob();
        // 搭桥实例
        FutureTask<Boolean> hTask = new FutureTask<>(hJob);

        // 异步线程
        Thread hThread = new Thread(hTask,"** 烧水-Thread");

        hThread.start();


        Callable<Boolean> wJob = new WashJob();
        FutureTask<Boolean> wTask = new FutureTask<>(wJob);

        Thread wThread = new Thread(wTask,"$$ 清洗-Thread");

        wThread.start();

        Thread.currentThread().setName("主线程");


        try {
            Boolean cupOk = wTask.get();
            Boolean waterOk = hTask.get();

            drinkTea(waterOk,cupOk);

        } catch (InterruptedException | ExecutionException e) {
            log.error("{}发送异常被中断", getCurThreadName(), e);
        }
        log.info("{}运行结束", getCurThreadName());
    }
}

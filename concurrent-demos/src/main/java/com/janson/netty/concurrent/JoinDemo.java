package com.janson.netty.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: join方法的应用场景：
 * A线程调用B线程的join方法，等待B线程执行完成，在B线程没有完成前，A线程阻塞。
 *
 * 【强调注意事项】
 * 1、join是实例方法，不是静态方法，需要使用线程对象去调用，如thread.join().
 * 2、join调用时，不是线程所指向的目标线程阻塞，而是当前线程阻塞。
 * 3、只有等到当前线程所指向的线程执行完成或者超时，当前线程才能重新恢复执行。
 *
 * 【join有一个问题】
 *  1、被合并的线程没有返回值。例如，在烧水的实例中，如果烧水线程的执行结束，main线程时无法知道
 *  结果的。同样的，清洗线程的执行结果，main线程也是无法知道的。形象地说，join线程合并就是一像一
 *  个闷葫芦。只能发起合并线程，不能取到执行结果。
 *
 *  如果需要获取异步线程的执行结果， 可以使用Java的FutureTask洗系列类。
 *
 * @Author: shanjian
 * @Date: 2022/10/11 9:55 上午
 */
@Slf4j
public class JoinDemo {

    public static final int SLEEP_GAP = 5000;

    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterThread extends Thread {

        public HotWaterThread() {
            super("** 烧水-Thread");
        }

        @Override
        public void run() {
            try {
                log.info("洗好水壶");
                log.info("灌上凉水");
                log.info("放在火上");

                // 线程睡眠一段时间，代表烧水中
                Thread.sleep(SLEEP_GAP);
                log.info("水开了");
            } catch (InterruptedException e) {
                log.error("发生异常被中断", e);
            }
            log.info("{}运行结束", getCurThreadName());
        }
    }

    static class WashThread extends Thread {
        public WashThread() {
            super("$$ 清洗-Thread");
        }

        @Override
        public void run() {
            try {
                log.info("洗茶壶");
                log.info("洗茶杯");
                log.info("拿茶叶");

                // 线程睡眠一段时间，代表清洗中
                Thread.sleep(SLEEP_GAP / 5);
                log.info("洗完了");
            } catch (InterruptedException e) {
                log.error("发生异常被中断", e);
            }
            log.info("{}运行结束", getCurThreadName());
        }
    }

    public static void main(String[] args) {
        Thread hThread = new HotWaterThread();
        Thread wThread = new WashThread();
        Thread.currentThread().setName("主线程");

        hThread.start();
        wThread.start();

        try {
            log.info("刷一会手机");

            // 合并清洗-线程
            wThread.join(20000);

            // 合并烧水-线程
            hThread.join(20000);

            log.info("泡茶喝");
        } catch (InterruptedException e) {
            log.error("{}发送异常被中断", getCurThreadName(), e);
        }
        log.info("{}运行结束", getCurThreadName());
    }
}

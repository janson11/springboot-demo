package com.janson.mutithread.basic.create;

import com.janson.util.Print;

import java.util.concurrent.atomic.AtomicInteger;

import static com.janson.util.ThreadUtil.getCurThreadName;
import static com.janson.util.ThreadUtil.sleepMilliSeconds;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/3/13 22:39
 **/
public class SalesDemo {

    public static final int MAX_AMOUT = 5;// 商品信息

    // 商店商品的销售线程，每条线程异步销售4次

    static class StoreGoods extends Thread {
        StoreGoods(String name) {
            super(name);
        }

        private int goodsAmount = MAX_AMOUT;

        public void run() {
            for (int i = 0; i < MAX_AMOUT; i++) {
                if (this.goodsAmount > 0) {
                    Print.cfo(getCurThreadName() + " 卖出一件，还剩：" + (--goodsAmount));
                    sleepMilliSeconds(10);
                }
            }
            Print.cfo(getCurThreadName() + " 运行结束.");
        }
    }

    // 商场商品的target销售目标类，一个商品最多销售4次，可多人销售。
    static class MallGoods implements Runnable {

        // 多人销售，可能导致数据出错，使用原子数据类型保障数据安全。
        private AtomicInteger goodsAmount = new AtomicInteger(MAX_AMOUT);


        @Override
        public void run() {
            for (int i = 0; i < MAX_AMOUT; i++) {
                if (this.goodsAmount.get() > 0) {
                    Print.cfo(getCurThreadName() + " 卖出一件，还剩：" + (goodsAmount.decrementAndGet()));
                    sleepMilliSeconds(10);
                }
            }
            Print.cfo(getCurThreadName() + " 运行结束.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Print.hint("商店版本的销售");
        for (int i = 1; i <= 2; i++) {
            Thread thread = null;
            thread = new StoreGoods("店员-" + i);
            thread.start();
        }

        Thread.sleep(1000);
        Print.hint("商场的商品销售");
        MallGoods mallGoods = new MallGoods();
        for (int i = 1; i <= 2; i++) {
            Thread thread = null;
            thread = new Thread(mallGoods, "商场销售员-" + i);
            thread.start();
        }

        Print.cfo(getCurThreadName() + " 运行结束.");
    }
}
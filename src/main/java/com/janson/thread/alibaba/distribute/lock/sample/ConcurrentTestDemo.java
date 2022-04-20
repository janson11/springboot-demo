package com.janson.thread.alibaba.distribute.lock.sample;

import org.springframework.ui.context.Theme;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/18 10:57 上午
 */
public class ConcurrentTestDemo {

    public static void main(String[] args) {
        // 并发数
        int currency = 20;
        // 循环屏障
        CyclicBarrier cb = new CyclicBarrier(currency);
//        CountDownLatch cdl = new CountDownLatch(currency);

//        OrderService orderService = new OrderServiceImplWithLock();
//        OrderService orderService = new OrderServiceImpl();


        // 多线程模拟高并发
        for (int i = 0; i < currency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "--------我准备好----------");
                    // 等待一起出发
                    OrderService orderService = new OrderServiceImplWithLock();
                    try {
                        cb.await();

                        /**
                         * cdl.countDown();
                         *cdl.await();
                         *
                         */
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    // 调用创建订单
                    orderService.createOrder();

                }
            }).start();
        }

    }
}

package com.janson.springboot.demo;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;


import java.util.concurrent.CountDownLatch;


/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/6/26 5:11 PM
 */

public class Log4jDemo extends SpringbootDemoApplicationTests{
    Logger logger = Logger.getLogger(Log4jDemo.class);

    /**
     *  单线程【ms】
     * (1)单线程，未开启缓存，立即输出
     * log4j:24657
     *
     * (2)单线程，开启缓存，不立即输出
     * log4j:6561
     *
     * (3)单线程，异步appender，未开启缓存，立即输出
     * log4j:62940
     *
     * (4)单线程，异步appender，开启缓存，不立即输出
     * log4j:17565
     *
     *  多线程【ms】
     * (1)单线程，未开启缓存，立即输出
     * log4j:37524
     *
     * (2)单线程，开启缓存，不立即输出
     * log4j:9384
     *
     * (3)单线程，异步appender，未开启缓存，立即输出
     * log4j:79995
     *
     * (4)单线程，异步appender，开启缓存，不立即输出
     * log4j:20439
     */
    @Test
    public void testLog4j() {
        int X_NUM = 100;
        int Y_NUM = 100000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < X_NUM; i++) {
            for (int j = 0; j < Y_NUM; j++) {
                logger.info("log4j Info Message!");
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }


    @Test
    public void testLog4jThread() throws InterruptedException {
        int THREAD_NUM = 100;
        final int LOOP_NUM = 100000;

        final CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < LOOP_NUM; j++) {
                        logger.info("log4j Info Message!");
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println(System.currentTimeMillis() - start);
    }


}

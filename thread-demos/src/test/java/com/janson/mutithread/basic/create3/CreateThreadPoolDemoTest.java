package com.janson.mutithread.basic.create3;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.janson.util.ThreadUtil.sleepSeconds;
import static org.junit.Assert.*;

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

}
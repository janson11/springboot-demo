package com.janson.springboot.demo.hutool;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: hutool test
 * @Author: shanjian
 * @Date: 2023/8/30 11:02 AM
 */
public class HuToolTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(2);


    /**
     * Exception in thread "main" java.util.ConcurrentModificationException
     * at java.util.LinkedHashMap$LinkedHashIterator.nextNode(LinkedHashMap.java:719)
     * at java.util.LinkedHashMap$LinkedKeyIterator.next(LinkedHashMap.java:742)
     * at com.janson.springboot.demo.hutool.HuToolTest.main(HuToolTest.java:21)
     *
     * @param args
     */
    public static void main(String[] args) {
//        multiThreadTestLinkedHashMap();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                singleThreadTestLinkedHashMap();
            }
        });
    }


    /**
     * 单线程测试LinkedHashMap
     */
    private static void singleThreadTestLinkedHashMap() {

        Map<Integer, String> processes = new LinkedHashMap<Integer, String>();
        processes.put(1, "1");
        processes.put(2, "2");
        processes.put(3, "3");

        System.out.println("执行remove前："+processes.toString());
        Iterator<Integer> iterator = processes.keySet().iterator();
        while (iterator.hasNext()) {
            if (processes.get(iterator.next()) != null) {
                iterator.remove();
            }
        }
        System.out.println("执行remove后："+processes.toString());
    }

    private static void multiThreadTestLinkedHashMap() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(new NormalTask(cyclicBarrier), "线程1").start();
        new Thread(new FinalTask(cyclicBarrier), "线程2").start();
    }


    private static class NormalTask implements Runnable {
        CyclicBarrier barrier;

        NormalTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            singleThreadTestLinkedHashMap();
            System.out.println(Thread.currentThread().getName() + "时间" + System.currentTimeMillis());
            try {
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println(System.currentTimeMillis() + " first step finished");
        }
    }

    private static class FinalTask implements Runnable {
        CyclicBarrier barrier;

        FinalTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            singleThreadTestLinkedHashMap();
            System.out.println(Thread.currentThread().getName() + "时间" + System.currentTimeMillis());
            try {
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println(System.currentTimeMillis() + " second step finished");
        }
    }


}

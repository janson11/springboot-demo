package com.janson.thread.alibaba.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/1 2:21 下午
 */
public class FutureDemo {
    public static void main(String[] args) throws InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName()+" 执行call方法，sleep");
                Thread.sleep(2000L);
                System.out.println(Thread.currentThread().getName()+" sleep后继续执行");

                return "ok";
            }
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread t1 = new Thread(futureTask);
        t1.start();

        System.out.println("main 执行 futureTask.get() at "+System.currentTimeMillis());
        try {
            String res = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("main 得到futureTask.get() 返回值 at "+System.currentTimeMillis());
        futureTask = new FutureTask<>(callable);
        Thread t2 = new Thread(futureTask);
        t2.start();
        Thread.sleep(100L);
        System.out.println("main执行cancel 返回:"+futureTask.cancel(false));

         callable = new Callable<String>() {
             @Override
             public String call() throws Exception {
                 System.out.println(Thread.currentThread().getName() +" 执行call方法，sleep");
                 while (!Thread.interrupted()) {
                     System.out.println(Thread.currentThread().getName()+"...");
                 }
                 System.out.println(Thread.currentThread().getName() +" sleep后继续执行");
                 return "ok";
             }
         };

         futureTask = new FutureTask<>(callable);
         Thread t3 = new Thread(futureTask);
         t3.start();
         Thread.sleep(100L);
        System.out.println("main执行cancel 返回："+futureTask.cancel(true));

        // 线程正在执行，任务中没有判断中断状态，则取消不了任务
        FutureTask<Void> futureTask1 = new FutureTask<>( ()->{
            while (true){

            }
        });

        Thread t4= new Thread(futureTask1);
        t4.start();
        Thread.sleep(100L);
        System.out.println("main 执行futureTask1 cancel 返回："+futureTask1.cancel(true));




    }

}

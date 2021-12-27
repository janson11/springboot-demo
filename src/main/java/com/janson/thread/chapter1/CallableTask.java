package com.janson.thread.chapter1;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description: 有返回值的Callable创建线程
 * @Author: Janson
 * @Date: 2021/12/26 23:40
 **/
public class CallableTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return new Random().nextInt();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 提交任务并用Future提交返回结果
        Future<Integer> submit = service.submit(new CallableTask());
        System.out.println(submit.get());

    }
}

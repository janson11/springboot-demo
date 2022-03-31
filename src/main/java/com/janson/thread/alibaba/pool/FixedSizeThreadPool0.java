package com.janson.thread.alibaba.pool;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/31 2:53 下午
 */
public class FixedSizeThreadPool0 {

    /**
     * 任务队列
     */
    private BlockingQueue<Runnable> taskQueue;

    /**
     工作线程
     */
    List<Worker> workers;

    private boolean working=true;

    public FixedSizeThreadPool0(int poolSize,int queueSize) {
        taskQueue = new LinkedBlockingQueue<>(queueSize);
        workers = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }

    public boolean submit(Runnable task) {
        if (working) {
            return taskQueue.offer(task);
        }
        return false;
    }

    public void  shutdown() {
        // 执行了关闭后，线程池应该是一个怎样的工作状态？
        /**
         * 1、不能接收新任务
         * 2、如果当前还有任务没有执行怎么办？
         * 比如：规定 执行完已提交的任务，再关闭线程池。
         *
         */
        this.working =false;
        // 当执行shutdown的时候，应该有线程阻塞，要想办法结束线程。
        // 遍历线程，如果线程当前是阻塞的状态，停止线程。如果线程当前是运行状态，不能停止线程。
        for (Worker t : workers) {
            Thread.State state = t.getState();
            if (state ==Thread.State.BLOCKED || state ==Thread.State.WAITING || state == Thread.State.TIMED_WAITING) {

            }
        }

    }

    private static class Worker extends Thread {

        private FixedSizeThreadPool0 pool;

        public Worker(FixedSizeThreadPool0 pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while(pool.working || pool.taskQueue.size() >0) {
                Runnable task = null;
                try {
                    if (pool.working) {
                        task = pool.taskQueue.take();
                    } else {
                        task = pool.taskQueue.poll();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task!=null) {
                    task.run();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FixedSizeThreadPool0 pool = new FixedSizeThreadPool0(3,6);
        for (int i = 0; i < 5; i++) {
            pool.submit( () ->{
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(Thread.currentThread().getName() +" 执行完任务");
            });
        }

        Thread.sleep(400);
        pool.shutdown();
    }
}

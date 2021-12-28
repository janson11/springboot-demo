package com.janson.thread.chapter9;

/**
 * @Description: 单个任务的时候，新建线程来执行
 *
 * @Author: shanjian
 * @Date: 2021/12/28 10:49 上午
 */
public class OneTask {

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }

}

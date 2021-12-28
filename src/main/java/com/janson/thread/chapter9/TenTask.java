package com.janson.thread.chapter9;

/**
 * @Description: for循环新建10个线程
 * @Author: shanjian
 * @Date: 2021/12/28 10:49 上午
 */
public class TenTask {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }

}

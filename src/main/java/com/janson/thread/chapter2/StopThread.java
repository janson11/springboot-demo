package com.janson.thread.chapter2;


/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/12/27 2:04 下午
 */
public class StopThread implements Runnable {


    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 1000) {
            System.out.println("count= " + count++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        thread.stop();
        thread.suspend();
        thread.resume();
        Thread.sleep(10);
        thread.interrupt();
    }
}

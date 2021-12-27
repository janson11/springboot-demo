package com.janson.thread.chapter2;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/12/27 2:17 下午
 */
public class StopDuringSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (!Thread.currentThread().isInterrupted() && num <= 1000) {
                    System.out.println(num);
                    num++;
                    Thread.sleep(1000000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
    }
}

package com.janson.thread.chapter27;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: ThreadLocal 典型场景一：
 * ThreadLocal 用作保存每个线程独享的对象，为每个线程都创建一个副本，这样每个线程都可以修改自己所拥有的副本,
 * 而不会影响其他线程的副本，确保了线程安全
 * @Author: Janson
 * @Date: 2022/1/9 11:59
 **/
public class ThreadLocalDemo2 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                String date = new ThreadLocalDemo2().date(finalI);
                System.out.println(Thread.currentThread().getName() + ":" + date);
            }).start();
        }
        Thread.sleep(100);
    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(date);
    }
}

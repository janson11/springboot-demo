package com.janson.thread.alibaba.interrupt;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/1 11:29 上午
 */
public class InterruptStateDemo {
    static volatile boolean flag =true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            while (flag) {
            }
            System.out.println(Thread.currentThread().getName() +"执行interrupted()的返回："+Thread.interrupted());
            while (!flag) {

            }
        });

        t1.start();
        Thread.sleep(100L);
        System.out.println("interrupt()前的状态："+t1.isInterrupted());
        System.out.println("执行interrupt()");
        t1.interrupt();

        System.out.println("interrupt()后的状态："+t1.isInterrupted());
        System.out.println("设置flag=false");
        flag =false;
        Thread.sleep(100L);
        System.out.println("从main中再次获取t1的中断状态："+t1.isInterrupted());
        flag =true;

        Thread t2 = new Thread( ()->{
            System.out.printf("t2 sleep(2000L)");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2中获取自己的中断状态："+Thread.currentThread().isInterrupted());
        });
        t2.start();
        Thread.sleep(100L);
        System.out.println("从main中断t2");
        t2.interrupt();


    }
}

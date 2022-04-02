package com.janson.thread.alibaba.communication;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/2 5:26 下午
 */
public class Demo1_WaitNotify {
    static int i =0;
    static Object obj = new Object();

    public static void main(String[] args) {
        new Thread( () ->{
           synchronized (obj) {
               while (i<10) {
                   System.out.println("t1: "+(++i));
                   obj.notify();

                   try {
                       obj.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               obj.notify();
           }
        }).start();

        new Thread( ()->{
            synchronized (obj) {
                while (i<10) {
                    System.out.println("  t2: "+(++i));
                    obj.notify();
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                obj.notify();
            }
        }).start();
    }
}

package com.janson.thread.alibaba.communication;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/2 5:55 下午
 */
public class Demo2_LockSupport {

    static int i =0;
    static Thread t1,t2;

    public static void main(String[] args) {
        t1 = new Thread( () ->{
            while (i<10) {
                System.out.println("t1: "+(++i));
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread( () ->{
            while (i<10) {
                LockSupport.park();
                System.out.println("  t2: "+(++i));
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }

}

/*
package com.janson.interview;

import java.util.concurrent.locks.LockSupport;

*/
/**
 * @Description: 1A2B3C  交替输出问题
 * 用两个线程，一个输出字母，一个输出数字，交替输出1A2B3C4D...26Z
 * @Author: Janson
 * @Date: 2021/12/22 21:08
 **//*

public class Test1A2B3C {
    public static void main(String[] args) {
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        LockSupport t1;
        LockSupport t2;

        t1 = new Thread(() -> {
            for (char c : aI) {
                System.out.println(c);
                // 叫醒t2
                LockSupport.unpark(t2);
                // t1阻塞，当前线程阻塞
                LockSupport.park();
            }
        }, "t1");


        t2 = new Thread(() -> {
            for (char c : aC) {
                // t2 挂起
                LockSupport.park();
                System.out.println(c);
                // 叫醒
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();

    }
}
*/

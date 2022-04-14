package com.janson.thread.alibaba.volat;

import org.checkerframework.checker.units.qual.C;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/14 10:45 上午
 */
public class ClassA {

    static int count = 0;

    // 锁住的this对象，实例
    public synchronized void do1() {
        count++;
        System.out.println("do1："+count);

    }

    // 锁住的当前类ClassA.class
    public static synchronized void do2() {
        count++;
        System.out.println("do2："+count);
    }

    public void do3() {
        // 锁住的this对象，实例
        synchronized (this) {
            count++;
            System.out.println("do3："+count);
        }
    }

    public static void main(String[] args) {

        // 对象实例a
        ClassA a = new ClassA();
        a.do1();

        // 对象实例b
        ClassA b = new ClassA();
        b.do3();

        // 如果是多线程的话，就不能保证线程安全的，因为是两把锁，两个实例（this）
        // 如果想要在多线程保证线程安全，必须采用do2()方法，加到类上。

    }
}

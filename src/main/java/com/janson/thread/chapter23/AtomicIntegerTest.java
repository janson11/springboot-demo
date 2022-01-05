package com.janson.thread.chapter23;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/1/5 6:53 下午
 */
public class AtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println("1:   "+atomicInteger.get());
        atomicInteger.getAndIncrement();
        System.out.println("2:   "+atomicInteger.get());
        int andSet = atomicInteger.getAndSet(2);
        System.out.println("3:    "+andSet);
        System.out.println("3 1:  "+atomicInteger.get());
    }
}

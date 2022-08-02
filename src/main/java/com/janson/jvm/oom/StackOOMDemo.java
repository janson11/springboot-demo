package com.janson.jvm.oom;

/**
 * @Description: 栈内存溢出示例
 *
 * -XX:ThreadStackSize=1m
 *
 *
 * 目前是第5782次调用方法
 * Exception in thread "main" java.lang.StackOverflowError
 *
 * @Author: shanjian
 * @Date: 2022/8/2 9:22 上午
 */
public class StackOOMDemo {

    private static long counter = 0;


    public static void main(String[] args) {
        work();
    }

    public static void work() {
        System.out.println("目前是第"+(++counter)+"次调用方法");
        work();
    }
}

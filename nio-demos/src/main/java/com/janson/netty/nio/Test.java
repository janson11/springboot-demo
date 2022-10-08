package com.janson.netty.nio;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/8 4:04 下午
 */
public class Test {

    /**
     * << 左移运算符 1 << 1 相当于1（二进制）除以2
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(1 << 0);
        // 0000 0001
        // 0000 0100 = 2^2=4
        System.out.println(1 << 2);
        // 0000 0001
        // 0000 1000 = 2^3=8
        System.out.println(1 << 3);
        // 0000 0001
        // 0001 0000 = 2^4=16
        System.out.println(1 << 4);
    }
}

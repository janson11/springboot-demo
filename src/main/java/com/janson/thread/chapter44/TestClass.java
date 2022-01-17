package com.janson.thread.chapter44;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/17 18:01
 **/
public class TestClass {
    int p = 20;

    public static void main(String[] args) {
        final TestClass testClass = new TestClass();
        testClass.p = 30;
        System.out.println(testClass.p);
    }
}

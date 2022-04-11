package com.janson.java8.demo;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/11 2:29 下午
 */
public class TestIMyInterface {
    public static void main(String[] args) {
        IMyInterface iMyInterface = () -> System.out.println("I like study");
        iMyInterface.study();
    }
}

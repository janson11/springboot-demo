package com.janson.java8.demo;

/**
 * @Description: 函数式接口
 * @Author: Janson
 * @Date: 2021/11/26 8:50
 **/
public class FunctionalInterfaceDemo {

    public static void main(String[] args) {
        GreetingService greetingService = message -> System.out.println("Hello " + message);

        greetingService.sayMessage("消息");
    }
}

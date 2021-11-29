package com.janson.java8.demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Description: 函数式接口
 * @Author: Janson
 * @Date: 2021/11/26 8:50
 **/
public class FunctionalInterfaceDemo {

    public static void main(String[] args) {
        GreetingService greetingService = message -> System.out.println("Hello " + message);

        greetingService.sayMessage("消息");

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("输出所有数据：");
        eval(list, n -> true);
        System.out.println("输出所有偶数：");
        eval(list, n -> n % 2 == 0);
        System.out.println("输出大于3的所有数字");
        eval(list, n -> n > 3);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }

        }
    }
}

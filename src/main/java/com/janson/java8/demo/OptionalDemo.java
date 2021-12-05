package com.janson.java8.demo;

import java.util.Optional;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2021/12/5 18:16
 **/
public class OptionalDemo {
    public static void main(String[] args) {
        OptionalDemo optionalDemo = new OptionalDemo();
        Integer value1 = null;
        Integer value2 = new Integer(10);
        Optional<Integer> a = Optional.ofNullable(value1);
        Optional<Integer> b = Optional.of(value2);
        System.out.println("sum:" + optionalDemo.sum(a, b));

    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {
        System.out.println("第一个参数值存在：" + a.isPresent());
        System.out.println("第二个参数值存在：" + b.isPresent());
        Integer value1 = a.orElse(new Integer(0));
        Integer value2 = b.get();
        return value1 + value2;
    }
}

package com.janson.mybatis.demo.invoke;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/12/17 2:33 下午
 */
public class SubMethodHandleDemo extends MethodHandleDemo {
    @Override
    public String sayHello(String s) {
        return "Sub Hello," + s;
    }
}

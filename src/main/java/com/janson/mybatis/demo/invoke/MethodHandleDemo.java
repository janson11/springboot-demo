package com.janson.mybatis.demo.invoke;

import net.bytebuddy.implementation.MethodDelegation;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @Description: MethodHandle demo
 * @Author: shanjian
 * @Date: 2021/12/17 2:21 下午
 */
public class MethodHandleDemo {

    public String sayHello(String s) {
        return "Hello, " + s;
    }

    public static void main(String[] args) throws Throwable {
        MethodHandleDemo subMethodHandleDemo = new SubMethodHandleDemo();
        MethodType methodType = MethodType.methodType(String.class, String.class);
        MethodHandle methodHandle = MethodHandles.lookup()
                .findVirtual(MethodHandleDemo.class, "sayHello", methodType);
        System.out.println(methodHandle.bindTo(subMethodHandleDemo)
                .invokeWithArguments("MethodHandleDemo"));
        MethodHandleDemo methodHandleDemo = new MethodHandleDemo();
        System.out.println(methodHandle.bindTo(methodHandleDemo)
        .invokeWithArguments("MethodHandleDemo"));


    }
}

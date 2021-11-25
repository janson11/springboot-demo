package com.janson.java8.demo;

/**
 * @Description: lambda变量作用域 在 Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量
 * @Author: Janson
 * @Date: 2021/11/25 9:46
 **/
public class LambdaDemo1 {


    public static void main(String[] args) {

        final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);

    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }
}

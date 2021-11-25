package com.janson.java8.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 方法引用通过方法的名字来指向一个方法。
 * <p>
 * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 * <p>
 * 方法引用使用一对冒号 :: 。
 * @Author: Janson
 * @Date: 2021/11/25 22:25
 **/
public class MethodReferenceDemo {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(System.out::println);
    }


}

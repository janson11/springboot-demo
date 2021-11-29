package com.janson.java8.demo.reference;

import org.apache.ibatis.logging.stdout.StdOutImpl;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2021/11/29 9:26
 **/
public interface Vechicle {

    default void print() {
        System.out.println("我是一辆车!");
    }

    // 静态默认方法
    static void blowHorn(){
        System.out.println("按喇叭!!!");
    }
}

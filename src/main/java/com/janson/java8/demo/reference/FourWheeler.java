package com.janson.java8.demo.reference;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2021/11/29 9:28
 **/
public interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车!");
    }
}

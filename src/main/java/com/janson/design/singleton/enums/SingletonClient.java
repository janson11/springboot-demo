package com.janson.design.singleton.enums;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/13 11:29 上午
 */
public class SingletonClient {
    public static void main(String[] args) {
        Singleton singleton = Singleton.UNIQUE_INSTANCE;
        System.out.println(singleton.getDescription());

    }
}

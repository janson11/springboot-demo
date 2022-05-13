package com.janson.design.singleton.classic;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/13 10:50 上午
 */
public class SingletonClient {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.getDescription());
    }
}

package com.janson.design.singleton.stat;

/**
 * @Description: 饿汉式单例模式
 * @Author: shanjian
 * @Date: 2022/5/13 10:53 上午
 */
public class SingletonClient {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.getDescription());

    }

}

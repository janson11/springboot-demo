package com.janson.design.singleton.stat;

/**
 * @Description: 饿汉式单例模式
 * @Author: shanjian
 * @Date: 2022/5/13 10:53 上午
 */
public class Singleton {

    private static Singleton uniqueInstance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return uniqueInstance;
    }

    public String getDescription(){
        return "I'm a statically initialized Singleton!";
    }

}

package com.janson.design.singleton.classic;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/13 10:43 上午
 */
public class Singleton {

    /**
     * 私有静态成员变量
     */
    private static Singleton uniqueInstance;

    /**
     * 私有的构造器
     */
    private Singleton(){}

    /**
     * 公开静态获取实例的方法
     */
    public static Singleton getInstance(){
        if (uniqueInstance==null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

    public String getDescription(){
        return "I'm a classic Singleton!";
    }


}

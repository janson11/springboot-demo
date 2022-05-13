package com.janson.design.singleton.dcl;

/**
 * @Description: 双重检查加锁(double-checked locking)
 * @Author: shanjian
 * @Date: 2022/5/13 11:05 上午
 */
public class Singleton {

    /**
     * 私有静态的多线程可见性的成员变量
     */
    private static volatile Singleton uniqueInstance;

    /**
     * 私有构造器
     */
    private Singleton(){}

    public static Singleton getInstance(){
        if (uniqueInstance==null) {
            synchronized (Singleton.class) {
                if (uniqueInstance==null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }



}

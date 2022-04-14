package com.janson.thread.alibaba.volat;

/**
 * @Description:   DCL 双重检查+锁
 * 1、私有的静态实例 可见性volatile
 * 2、私有的构造器
 * 3、双重检查+锁
 * @Author: shanjian
 * @Date: 2022/4/14 11:35 上午
 */
public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {
    }



    public static Singleton getInstance() {
        // 第一次检查
        if (instance == null) {
            // 创建实例
            synchronized (Singleton.class) {
                // 第二次检查
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}

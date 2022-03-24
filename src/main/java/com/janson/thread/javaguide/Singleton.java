package com.janson.thread.javaguide;

/**
 * @Description: 单例
 * @Author: shanjian
 * @Date: 2022/3/24 2:30 下午
 */
public class Singleton {
    // 使用volatile可以禁止JVM的指令重排，保证在多线程环境也能正常执行。
    private static volatile Singleton singleton;

    private Singleton() {

    }

    public static Singleton getInstance() {
        // 先判断对象是否已经实例过，没有实例过才能进入加锁代码
        if (singleton == null) {
            // 类实例加锁
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

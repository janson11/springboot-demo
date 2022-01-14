package com.janson.thread.chapter38;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/14 21:45
 **/
public class Singleton {
    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
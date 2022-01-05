package com.janson.thread.chapter12;

/**
 * @Description: 同步方法
 * @Author: Janson
 * @Date: 2022/1/3 10:49
 **/
public class SyncMethodTest {
    public synchronized void synMethod() {
        System.out.println("sync method");
    }
}

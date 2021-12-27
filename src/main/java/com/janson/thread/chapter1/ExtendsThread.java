package com.janson.thread.chapter1;

/**
 * @Description: 继承Thread类
 * @Author: Janson
 * @Date: 2021/12/26 18:12
 **/
public class ExtendsThread extends Thread {
    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }
}

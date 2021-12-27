package com.janson.thread.chapter1;

/**
 * @Description: 实现Runnable接口
 * @Author: Janson
 * @Date: 2021/12/26 18:08
 **/
public class RunnableThread implements Runnable {
    @Override
    public void run() {
        System.out.println("用实现Runnable接口实现线程");
    }
}

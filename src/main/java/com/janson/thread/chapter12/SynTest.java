package com.janson.thread.chapter12;

/**
 * @Description: 同步代码块
 * @Author: shanjian
 * @Date: 2021/12/30 6:54 下午
 */
public class SynTest {
    public void synBlock(){
        synchronized (this) {
            System.out.println("xiaoLi");
        }
    }
}

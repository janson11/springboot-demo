package com.janson.thread.javaguide;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/24 2:47 下午
 */
public class SynchronizedDemo {
    public void method(){
        synchronized (this){
            System.out.println("synchronized 代码块");
        }
    }
}

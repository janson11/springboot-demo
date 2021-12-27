package com.janson.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 三个线程循环输出ABCABC，每个线程输出10次
 * @Author: Janson
 * @Date: 2021/12/22 20:57
 **/
public class TestABCABC {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        lock.lock();
        try {
            new Thread(() ->{
                System.out.println("A");
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        } finally {
            lock.unlock();
        }


    }


}

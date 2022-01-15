package com.janson.thread.chapter39;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/15 10:05
 **/
public class SimulateCAS {
    private int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == newValue) {
            value = newValue;
        }
        return oldValue;
    }
}

package com.janson.thread.chapter2;

import java.util.concurrent.BlockingQueue;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/12/27 5:11 下午
 */
public class Consumer {

    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.97) {
            return false;
        }
        return true;
    }

}

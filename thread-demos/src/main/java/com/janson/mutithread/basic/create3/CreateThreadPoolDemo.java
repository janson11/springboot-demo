package com.janson.mutithread.basic.create3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/4/2 22:38
 **/
public class CreateThreadPoolDemo {

    public static final int SLEEP_GAP = 500;
    public static final int MAX_TURN = 5;

    // 异步的执行目标类
    public static class TargetTask implements Runnable {

        static AtomicInteger taskNo = new AtomicInteger(1);
        protected String taskName;

        @Override
        public void run() {
            taskName = "task-"+taskNo.get();
            taskNo.incrementAndGet();
        }
    }


}

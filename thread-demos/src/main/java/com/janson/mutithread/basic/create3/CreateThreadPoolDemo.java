package com.janson.mutithread.basic.create3;

import com.janson.util.Print;

import java.util.concurrent.atomic.AtomicInteger;

import static com.janson.util.ThreadUtil.sleepMilliSeconds;

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

        public TargetTask() {
            taskName = "task-" + taskNo.get();
            taskNo.incrementAndGet();
        }

        @Override
        public void run() {
            Print.tco("任务：" + taskName + " doing");
            // 线程睡眠一会
            sleepMilliSeconds(SLEEP_GAP);
            Print.tco("任务：" + taskName + " 运行结束.");
        }
    }


}

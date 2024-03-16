package com.janson.mutithread.basic.use;

import com.janson.util.Print;

import static com.janson.util.ThreadUtil.sleepMilliSeconds;

import javax.annotation.security.RunAs;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/3/16 21:42
 **/
public class ThreadNameDemo {

    private static final int MAX_TURN = 3;

    //异步执行目标类
    static class RunTarget implements Runnable {

        // 实现Runnable接口
        @Override
        public void run() {
            // 重新run()方法
            for (int i = 0; i < MAX_TURN; i++) {
                sleepMilliSeconds(500);//线程休眠
                Print.tco("线程执行轮次：" + i);
            }
        }
    }

    public static void main(String[] args) {
        RunTarget target = new RunTarget();
        new Thread(target).start();
        new Thread(target).start();
        new Thread(target).start();
        new Thread(target, "手动命名线程-A").start();
        new Thread(target, "手动命名线程-B").start();
        sleepMilliSeconds(Integer.MAX_VALUE);
    }

}

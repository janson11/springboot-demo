package com.janson.mutithread.basic.create;

import com.janson.util.Print;

import static com.janson.util.ThreadUtil.getCurThreadName;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/3/6 23:07
 **/
public class CreateDemo2 {

    public static final int MAX_TURN = 5;

    static int threadNo = 1;

    // (1) 实现Runnable接口
    static class RunTarget implements Runnable {

        @Override
        public void run() {
            // (2) 在这些业务逻辑
            for (int j = 1; j < MAX_TURN; j++) {
                Print.cfo(getCurThreadName() + ",轮次： " + j);
            }
            Print.cfo(getCurThreadName() + "运行结束.");
        }
    }

    public static void main(String[] args) {
        Thread thread = null;
        // 方法2.1 使用实现Runnable的实现类创建和启动线程
        /**
         * 方法2.1 使用实现Runnable的实现类创建和启动线程
         * [CreateDemo2$RunTarget.run]: RunnableThread1,轮次： 1
         * [CreateDemo2$RunTarget.run]: RunnableThread1,轮次： 2
         * [CreateDemo2$RunTarget.run]: RunnableThread2,轮次： 1
         * [CreateDemo2$RunTarget.run]: RunnableThread1,轮次： 3
         * [CreateDemo2$RunTarget.run]: RunnableThread1,轮次： 4
         * [CreateDemo2$RunTarget.run]: RunnableThread1运行结束.
         * [CreateDemo2$RunTarget.run]: RunnableThread2,轮次： 2
         * [CreateDemo2$RunTarget.run]: RunnableThread2,轮次： 3
         * [CreateDemo2$RunTarget.run]: RunnableThread2,轮次： 4
         * [CreateDemo2$RunTarget.run]: RunnableThread2运行结束.
         */
        for (int i = 0; i < 2; i++) {
            Runnable target = new RunTarget();
            thread = new Thread(target, "RunnableThread" + threadNo++);
            thread.start();
        }
        System.out.println("方法2.1 使用实现Runnable的实现类创建和启动线程");

        // 方法2.2 使用实现Runnable的匿名类创建和启动线程
        /**
         * 方法2.2 使用实现Runnable的匿名类创建和启动线程
         * [CreateDemo2$1.run]: RunnableThread1,轮次： 1
         * [CreateDemo2$1.run]: RunnableThread2,轮次： 1
         * [CreateDemo2$1.run]: RunnableThread1,轮次： 2
         * [CreateDemo2$1.run]: RunnableThread2,轮次： 2
         * [CreateDemo2$1.run]: RunnableThread2,轮次： 3
         * [CreateDemo2$1.run]: RunnableThread2,轮次： 4
         * [CreateDemo2$1.run]: RunnableThread2运行结束.
         * [CreateDemo2$1.run]: RunnableThread1,轮次： 3
         * [CreateDemo2$1.run]: RunnableThread1,轮次： 4
         * [CreateDemo2$1.run]: RunnableThread1运行结束.
         */
//        for (int i = 0; i < 2; i++) {
//            thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int j = 1; j < MAX_TURN; j++) {
//                        Print.cfo(getCurThreadName() + ",轮次： " + j);
//                    }
//                    Print.cfo(getCurThreadName() + "运行结束.");
//                }
//            }, "RunnableThread" + threadNo++);
//            thread.start();
//        }
//        System.out.println("方法2.2 使用实现Runnable的匿名类创建和启动线程");

        // 方法2.3 使用实现lambar表达式创建和启动线程
        /**
         * 方法2.3 使用实现lambar表达式创建和启动线程
         * [CreateDemo2.lambda$main$0]: RunnableThread1,轮次： 1
         * [CreateDemo2.lambda$main$0]: RunnableThread2,轮次： 1
         * [CreateDemo2.lambda$main$0]: RunnableThread2,轮次： 2
         * [CreateDemo2.lambda$main$0]: RunnableThread2,轮次： 3
         * [CreateDemo2.lambda$main$0]: RunnableThread2,轮次： 4
         * [CreateDemo2.lambda$main$0]: RunnableThread2运行结束.
         * [CreateDemo2.lambda$main$0]: RunnableThread1,轮次： 2
         * [CreateDemo2.lambda$main$0]: RunnableThread1,轮次： 3
         * [CreateDemo2.lambda$main$0]: RunnableThread1,轮次： 4
         * [CreateDemo2.lambda$main$0]: RunnableThread1运行结束.
         */
//        for (int i = 0; i < 2; i++) {
//            thread = new Thread(() -> {
//                for (int j = 1; j < MAX_TURN; j++) {
//                    Print.cfo(getCurThreadName() + ",轮次： " + j);
//                }
//                Print.cfo(getCurThreadName() + "运行结束.");
//            }, "RunnableThread" + threadNo++);
//            thread.start();
//        }
//        System.out.println("方法2.3 使用实现lambar表达式创建和启动线程");

    }
}

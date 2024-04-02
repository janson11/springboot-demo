package com.janson.mutithread.basic.use;

import com.janson.util.Print;

import static com.janson.util.ThreadUtil.getCurThread;
import static com.janson.util.ThreadUtil.sleepMilliSeconds;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/3/21 22:17
 **/
public class DaemonDemo2 {

    public static final int SLEEP_GAP = 500;// 每一轮的睡眠时长
    public static final int MAX_TURN = 4;//用户线程池执行轮次

    //守护线程实现类
    static class NormalThread extends Thread {
        static int threadNo = 1;
        public NormalThread(){
            super("normalThread-"+threadNo);
        }

        @Override
        public void run() {
            for (int i = 0; ;i++){
                //线程睡眠一会
                sleepMilliSeconds(SLEEP_GAP);
                Print.synTco(getName()+", 守护状态为："+isDaemon());
            }
        }
    }

    public static void main(String[] args) {
        Thread daemon  = new NormalThread();
        daemon.setDaemon(true);
        daemon.start();

        Thread userThread = new Thread(() ->{
            Print.synTco(">>用户线程开始.");
            for (int i = 0; i <=MAX_TURN ; i++) {
                Print.synTco(">> 轮次："+i+"-守护状态为："+getCurThread().isDaemon());
                sleepMilliSeconds(SLEEP_GAP);
            }
        },"userThread");
        userThread.start();

        // 主线程合入userThread,等待userThread执行完成
//        userThread.join();
        Print.synTco(" 守护状态为："+getCurThread().isDaemon());
        Print.synTco(" 运行结束.");
    }
}

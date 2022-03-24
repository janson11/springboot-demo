package com.janson.thread.javaguide;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/24 10:37 上午
 */
public class MultiThread {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("threadId:"+threadInfo.getThreadId() +" threadName: "+threadInfo.getThreadName());
        }
    }
}

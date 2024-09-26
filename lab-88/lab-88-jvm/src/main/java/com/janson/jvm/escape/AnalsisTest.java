package com.janson.jvm.escape;

/**
 * @Description: 逃逸分析
 *
 * 逃逸分析是指在编译时期间，JVM通过数据流分析，判断一个对象是否可能逃逸到方法外部，如果逃逸到方法外部，则需要进行内存分配，
 * 不开启逃逸分析 -Xmx4G -Xms4G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * 开启逃逸分析 -Xmx4G -Xms4G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 *
 * @Author: Janson
 * @Date: 2024/9/26 11:05
 **/
public class AnalsisTest {

    public static void main(String[] args) {
        long a1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            alloc();
        }
        long a2 = System.currentTimeMillis();
        System.out.println("耗时：" + (a2 - a1) + "ms");

        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("执行完成");

    }


    private static void alloc() {
        User user = new User();
    }

    static class User {
    }

}



package com.janson.demo;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/12/18 11:35
 **/
public class nacosTest {

    public static void main(String[] args) {
        System.out.println(getSuitableThreadCount(8));
    }

    public static int getSuitableThreadCount(int threadMultiple) {
//        final int coreCount = Runtime.getRuntime().availableProcessors();
        final int coreCount = 16;
        int workerCount = 1;
        while (workerCount < coreCount * threadMultiple) {
            workerCount <<= 1;
        }
        return workerCount;
    }


}

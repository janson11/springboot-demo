package com.janson.jvm.demo;

/**
 * @Description: JVM JDK 1.8 demo测试
 * -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc2.log
 * -XX:NewSize 初始新生代大小 5242880B=5MB
 * -XX:MaxNewSize 最大新生代大小 5242880B=5MB
 * -XX:InitialHeapSize 初始堆大小 10485760 = 10MB
 * -XX:MaxHeapSize   最大堆大小 10485760 = 10MB
 * -XX:SurvivorRatio=8 新生代Eden区与Survivor2区的占比，默认是8：1：1【Eden:Survior1:Survior2】
 * -XX:PretenureSizeThreshold  大对象阈值 10485760=10MB
 *
 * -XX:+PrintGCDetails：打印详细的gc日志
 * -XX:+PrintGCTimeStamps：这个参数可以打印出来每次GC发生的时间
 * -Xloggc:gc.log：这个参数可以设置将gc日志写入一个磁盘文件
 *
 *
 * @Author: shanjian
 * @Date: 2022/7/12 10:20 上午
 */
public class Demo2 {
    public static void main(String[] args) {
        byte[] array1 = new byte[2* 1024 * 1024];
        array1 = new byte[2* 1024 * 1024];
        array1 = new byte[2* 1024 * 1024];
        array1 = null;
        byte[] array2 = new byte[128 * 1024];
        byte[] array3 = new byte[2* 1024 * 1024];

    }
}

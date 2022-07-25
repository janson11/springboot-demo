package com.janson.jvm.gc.example;

/**
 * @Description: 比如一个平台有数十万甚至上百万的商家在你的平台上做生意，会使用你的这个平台系统，此时一定会产生大量的数据
 * 然后基于这些数据我们需要为商家提供一些数据报表，比如：每个商家每天有多少访客？有多少交易？付费转化率是多少？当然实际情
 * 况会比这个简单几句话复杂很多，我们这里就简单说个概念而已。
 * 所以此时就需要一套BI系统，所谓BI，英文全称是“Business Intelligence”，也就是“商业智能”
 * -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200 -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:src/main/java/com/janson/jvm/gc/example/bigc1.log
 * 第一次优化下
 * 我们只需要调大年轻代的内存空间，增加Survivor的内存即可，看如下JVM参数：
 * -XX:NewSize=209715200 -XX:MaxNewSize=209715200 -XX:InitialHeapSize=314572800 -XX:MaxHeapSize=314572800 -
 * XX:SurvivorRatio=2 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC -
 * XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
 * 我们把堆大小调大为了300MB，年轻代给了200MB，同时“-XX:SurvivorRatio=2”表明，Eden:Survivor:Survivor的比例为2:1:1，
 * 所以Eden区是100MB，每个Survivor区是50MB，老年代也是100MB。
 *
 * -XX:NewSize=209715200 -XX:MaxNewSize=209715200 -XX:InitialHeapSize=314572800 -XX:MaxHeapSize=314572800 -XX:SurvivorRatio=2 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:src/main/java/com/janson/jvm/gc/example/bigc11.log
 * <p>
 * 新生代对象增长的速率
 * Young GC的触发频率
 * Young GC的耗时
 * 每次Young GC后有多少对象是存活下来的
 * 每次Young GC过后有多少对象进入了老年代
 * 老年代对象增长的速率
 * Full GC的触发频率
 * Full GC的耗时
 * <p>
 * jstat -gc 86684 1000 1000
 * 对进程86684 每秒(第一个参数) 打印1000次
 * @Author: shanjian
 * @Date: 2022/7/22 9:57 上午
 */
public class BiDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30000);
        while (true) {
            loadData();
        }

    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null;
        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];

        byte[] data3 = new byte[10 * 1024 * 1024];
        data3= new byte[10 * 1024 * 1024];

        Thread.sleep(1000);
    }
}

package com.janson.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 堆内存溢出示例
 *
 * 所以我们用下面的JVM参数来运行一下代码：-Xms10m -Xmx10m，我们限制了堆内存大小总共就只有10m，这样可以尽快触发堆内
 * 存的溢出。
 *
 * 当前创建了第360145个对象
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *
 * @Author: shanjian
 * @Date: 2022/8/2 9:29 上午
 */
public class HeapOOMDemo {

    public static void main(String[] args) {
        long counter = 0;
        List<Object> list = new ArrayList<Object>();
        while (true) {
            list.add(new Object());
            System.out.println("当前创建了第"+(++counter)+"个对象");
        }
    }
}

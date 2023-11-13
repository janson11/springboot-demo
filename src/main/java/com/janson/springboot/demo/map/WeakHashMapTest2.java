package com.janson.springboot.demo.map;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @Description:
 *
 *
 * @Author: shanjian
 * @Date: 2023/11/8 8:22 PM
 */
public class WeakHashMapTest2 {


    /**
     * 这次测试输出正常,不在出现内存溢出问题.
     *
     * 988 size0
     * 989 size0
     * 990 size0
     * 991 size0
     * 992 size0
     * 993 size0
     * 994 size0
     * 995 size0
     * 996 size0
     * 997 size0
     * 998 size0
     *
     * Process finished with exit code 0
     *
     *
     * 总结来说：WeakHashMap并不是你啥也干他就能自动释放内部不用的对象的，而是在你访问它的内容的时候释放内部不用的对象
     *
     * 问题讲清楚了,现在我们来梳理一下.了解清楚其中的奥秘.
     *
     * WeakHashMap实现弱引用，是因为它的Entry<K,V>是继承自WeakReference<K>的
     *
     * 在WeakHashMap$Entry<K,V>的类定义及构造函数里面是这样写的：
     *
     * @param args
     */
    public static void main(String[] args) {
        List<WeakHashMap<byte[][],byte[][]>> maps = new ArrayList<WeakHashMap<byte[][],byte[][]>>();
        for (int i = 0; i < 1000; i++) {
            WeakHashMap<byte[][],byte[][]> d = new WeakHashMap<byte[][],byte[][]>();
            d.put(new byte[1000][1000],new byte[1000][1000]);
            maps.add(d);
            System.gc();
            System.err.println(i);
            for (int j = 0; j < i; j++) {
                System.err.println(j+" size"+maps.get(j).size());
            }

        }
    }
}

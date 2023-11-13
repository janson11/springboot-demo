package com.janson.springboot.demo.map;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/11/9 11:07 AM
 */
public class WeakHashMapTest3 {

    /**
     * 如何证明key中的byte被回收了呢?可以通过内存溢出时导出的内存镜像进行分析,也可以通过如下的小测试得出结论:
     *
     * 把上面的value用小对象代替，
     *
     * 上面的代码，即使执行10000次也没有问题，证明key中的byte数组确实被回收了。
     *
     * for循环中每次都new一个新的WeakHashMap，在put操作后，虽然GC将WeakReference的key中的byte数组回收了，并将事件通知到了ReferenceQueue，但后续却没有相应的动作去触发 WeakHashMap 去处理 ReferenceQueue
     *
     * 所以 WeakReference 包装的key依然存在在WeakHashMap中，其对应的value也当然存在。
     *
     *  那value是何时被清除的呢?
     *
     * 对两个例子进行分析可知,例子二中的maps.get(j).size()触发了value的回收,那又如何触发的呢.查看WeakHashMap源码可知,size方法调用了expungeStaleEntries方法,该方法对vm要回收的的entry(quene中)进行遍历,并将entry的value置空,回收了内存.
     *
     * 所以效果是key在GC的时候被清除,value在key清除后访问WeakHashMap被清除.
     *
     * 疑问:key的quene与map的quene是同一个quene,poll操作会减少一个reference,那问题是key如果先被清除,expungeStaleEntries遍历quene时那个被回收的key对应的entry还能取出来么???
     *
     * 关于执行System.GC时,key中的byte数据如何被回收了,请见WeakReference referenceQuene
     *
     * 来这里：http://www.cnblogs.com/redcreen/archive/2011/02/15/1955289.html
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        List<WeakHashMap<byte[][],Object>> maps = new ArrayList<WeakHashMap<byte[][],Object>>();
        for (int i = 0; i < 10000; i++) {
            WeakHashMap<byte[][], Object> d = new WeakHashMap<byte[][], Object>();
            d.put(new byte[1000][1000], new Object());
            maps.add(d);
            System.gc();
            System.err.println(i);
        }
    }
}

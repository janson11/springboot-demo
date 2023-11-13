WeakHashMap实现弱引用，是因为它的Entry<K,V>是继承自WeakReference<K>的
public class WeakHashMap<K,V>
extends AbstractMap<K,V>
implements Map<K,V> {}

在WeakHashMap$Entry<K,V>的类定义及构造函数里面是这样写的：


    private static class Entry<K,V> extends WeakReference<Object> implements Map.Entry<K,V> {
        V value;
        final int hash;
        Entry<K,V> next;

        /**
         * Creates new entry.
         */
        Entry(Object key, V value,
              ReferenceQueue<Object> queue,
              int hash, Entry<K,V> next) {
            super(key, queue);
            this.value = value;
            this.hash  = hash;
            this.next  = next;
        }
请注意它构造父类的语句：“super(key, queue);”，传入的是key，因此key才是进行弱引用的，value是直接强引用关联在this.value之中.在System.gc()时，key中的byte数组进行了回收,而value依然保持(value被强关联到entry上,entry又关联在map中,map关联在arrayList中.).

如何证明key中的byte被回收了呢?可以通过内存溢出时导出的内存镜像进行分析,也可以通过如下的小测试得出结论:

把上面的value用小对象代替
for (int i = 0; i < 10000; i++) {
WeakHashMap<byte[][], Object> d = new WeakHashMap<byte[][], Object>();
d.put(new byte[1000][1000], new Object());
maps.add(d); System.gc();
System.err.println(i);
}
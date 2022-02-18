package com.janson.interview;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: 手动实现LRU缓存
 * @Author: shanjian
 * @Date: 2022/2/16 5:41 下午
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private final int CACHE_SIZE;

    /**
     * 传递进来最多能缓存多少数据
     *
     * @param cacheSize
     */
    public LRUCache(int cacheSize) {
        // true 表示让linkedHashMap按照访问顺序进行排序，最近访问的放在头部,最老访问的放在尾部。
        super((int) (Math.ceil(cacheSize/0.75)+1),0.75f,true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当map中的数量大于指定的缓存个数，就自动删除最老的数据。
        return size() >CACHE_SIZE;
    }
}
package com.janson.springboot.demo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: LinkedHashMap test
 * @Author: shanjian
 * @Date: 2023/8/30 2:18 PM
 */
public class LinkedHashMapTest {


    public static void main(String[] args) {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
        map.put(1, "bar");
        map.put(2, "bar2");
        map.put(3, "bar3");
        map.put(4, "bar4");
        map.put(5, "bar5");

        Set<Integer> keySet = map.keySet();
        System.out.println("key集合：" + keySet);

        Collection<String> values = map.values();
        System.out.println("values集合：" + values);

        for (Map.Entry<Integer, String> e : map.entrySet()) {
            System.out.println("key:" + e.getKey() + " value:" + e.getValue());
        }
    }
}
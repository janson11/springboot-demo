package com.janson.springboot.demo.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/11/8 8:12 PM
 */
public class MapTest {

    /**
     *
     * map:b:bbb
     * weakmap:b:bbb
     *
     * @param args
     */
    public static void main(String[] args) {
        String a = new String("a");
        String b = new String("b");
        Map weakMap = new WeakHashMap();
        Map map = new HashMap();
        map.put(a, "aaa");
        map.put(b, "bbb");

        weakMap.put(a, "aaa");
        weakMap.put(b, "bbb");

        map.remove(a);
        a = null;
        b = null;

        System.gc();
        Iterator i = map.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry en = (Map.Entry) i.next();
            System.out.println("map:" + en.getKey() + ":" + en.getValue());
        }

        Iterator j = map.entrySet().iterator();
        while (j.hasNext()) {
            Map.Entry en = (Map.Entry) j.next();
            System.out.println("weakmap:" + en.getKey() + ":" + en.getValue());
        }
    }


}

package com.janson.thread.chapter20;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/1/5 2:03 下午
 */
public class HashMapDemo {


    public static void main(String[] args) {
        HashMap map = new HashMap<Person, Integer>(1);
        for (int i = 0; i < 1000; i++) {
            Person person = new Person();
            map.put(person, null);

        }
        System.out.println("运行结束");
    }


}

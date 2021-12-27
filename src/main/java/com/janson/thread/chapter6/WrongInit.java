package com.janson.thread.chapter6;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 发布和初始化导致线程安全问题
 * @Author: Janson
 * @Date: 2021/12/27 21:23
 **/
public class WrongInit {
    private Map<Integer, String> students;

    public WrongInit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                students = new HashMap<>();
                students.put(1, "王小美");
                students.put(2, "钱二宝");
                students.put(3, "周三");
                students.put(4, "赵四");
            }
        }).start();
    }

    public Map<Integer, String> getStudents() {
        return students;
    }

    public static void main(String[] args) {
        WrongInit wrongInit = new WrongInit();
        Map<Integer, String> students = wrongInit.getStudents();
//        if (students != null) {
            System.out.println(students.get(1));
//        }
    }

}

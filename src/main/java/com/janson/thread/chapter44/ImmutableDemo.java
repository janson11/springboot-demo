package com.janson.thread.chapter44;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 这就是一个很好的“包含对象类型的成员变量的类的对象，具备不可变性”的例子。
 * @Author: Janson
 * @Date: 2022/1/17 18:04
 **/
public class ImmutableDemo {

    private final Set<String> lessons = new HashSet<>();

    public ImmutableDemo() {
        lessons.add("1");
        lessons.add("2");
        lessons.add("3");
    }

    public boolean isLesson(String name) {
        return lessons.contains(name);
    }
}

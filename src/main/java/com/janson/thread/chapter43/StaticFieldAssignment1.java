package com.janson.thread.chapter43;

/**
 * @Description: 演示final的static类变量的赋值时机
 * @Author: Janson
 * @Date: 2022/1/17 16:44
 **/
public class StaticFieldAssignment1 {
    private static final int a = 0;
    
}

class StaticFieldAssignment2 {
    private static final int a;

    static {
        a = 0;
    }
}

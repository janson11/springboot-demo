package com.janson.buddy;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/11/29 3:38 下午
 */
public class Interceptor {

    public static String intercept(String name) {
        return "String Hello " + name;
    }

    public static String intercept(int i) {
        return "int Hello " + i;
    }

    public static String intercept(Object o) {
        return "Object Hello" + o;
    }
}

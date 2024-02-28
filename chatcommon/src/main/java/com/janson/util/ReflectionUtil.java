package com.janson.util;

/**
 * @Description: 反射工具类
 * @Author: Janson
 * @Date: 2024/2/28 23:24
 **/
public class ReflectionUtil {


    /**
     * 获得调用方法的类名+方法名
     *
     * @return
     */
    public static String getNakeCallClassMethod() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        // 获取调用方法名
        String[] className = stack[3].getClassName().split("\\.");
        String fullName = className[className.length - 1] + "." + stack[3].getMethodName();
        return fullName;
    }
}

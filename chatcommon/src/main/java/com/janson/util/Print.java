package com.janson.util;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/2/27 9:13
 **/
public class Print {

    public static void tco(Object s) {
        String cft = "[" + Thread.currentThread().getName() + "]" + "：" + s;

    }
}

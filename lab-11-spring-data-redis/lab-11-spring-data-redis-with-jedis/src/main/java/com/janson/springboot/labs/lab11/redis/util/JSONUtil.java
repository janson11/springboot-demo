package com.janson.springboot.labs.lab11.redis.util;

import com.alibaba.fastjson.JSON;

/**
 * @Description: JSON 工具类
 * @Author: shanjian
 * @Date: 2024/3/13 14:35
 */
public class JSONUtil {

    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    public static String toJSONString(Object javaObject) {
        return JSON.toJSONString(javaObject);
    }


    public static byte[] toJSONBytes(Object javaObject) {
        return JSON.toJSONBytes(javaObject);
    }

}

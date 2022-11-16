package com.janson.netty.common.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.GsonBuilder;

/**
 * @Description: JSON序列化和反序列化工具类
 * @Author: shanjian
 * @Date: 2022/11/15 11:37 上午
 */
public class JsonUtil {

    // 谷歌GsonBuilder构造器
    static GsonBuilder gb = new GsonBuilder();
    static {
        // 不需要html escape
        gb.disableHtmlEscaping();
    }

    // 序列化：使用谷歌Gson将POJO转换成字符串
    public static String pojoToJson(java.lang.Object obj){
        String json = gb.create().toJson(obj);
        return json;
    }

    //反序列化 ：使用阿里Fastjson将字符串转换成POJO对象
    public  static <T> T jsonToPojo(String json,Class<T> tClass){
        T t = JSONObject.parseObject(json, tClass);
        return t;
    }
}

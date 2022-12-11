package com.janson.jvm.utils;


import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/12/11 14:50
 **/
public class Base64Utils {

    // 解码
    public static byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    // 编码
    public static String encode(String string) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(string.getBytes("utf-8"));
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(encode("Hello Class Initialized!"));
        System.out.println(new String(decode("SGVsbG8gQ2xhc3MgSW5pdGlhbGl6ZWQh")));


    }


}

package com.janson.jvm.classload;

import java.util.Base64;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/12/11 14:42
 **/
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            // 加载并初始化Hello类
            new HelloClassLoader().findClass("com.janson.jvm.classload.Hello").newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String helloBase64 = "SGVsbG8gQ2xhc3MgSW5pdGlhbGl6ZWQh";
        byte[] bytes = decode(helloBase64);
        return defineClass(name, bytes, 0, bytes.length);
    }

    public byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }

}

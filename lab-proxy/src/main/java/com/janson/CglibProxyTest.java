package com.janson;

import com.janson.handler.CglibMethodInterceptor;
import com.janson.service.Boy;
import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/9/2 14:52
 **/
public class CglibProxyTest {

    public static void main(String[] args) {
        // 利用cglib的代理类可以将内存中的class文件写入本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/shanjian/IdeaProjects/study/springboot-demo/lab-proxy/target/classes/com/janson/handler/class");
        Boy boy = (Boy) new CglibMethodInterceptor().getInstance(Boy.class);
        boy.findLove();
    }
}

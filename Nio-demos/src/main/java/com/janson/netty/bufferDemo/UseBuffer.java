package com.janson.netty.bufferDemo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.IntBuffer;

/**
 * @Description: 使用buffer
 * @Author: shanjian
 * @Date: 2022/9/14 10:45 上午
 */
//@Slf4j
public class UseBuffer {


    private static final Logger LOGGER = LoggerFactory.getLogger(UseBuffer.class);


    public static void main(String[] args) {
        allocateTest();
    }


    static IntBuffer intBuffer = null;

    public static void allocateTest() {
        // 调用allocate方法，而不是使用new
        intBuffer = IntBuffer.allocate(20);
        //输出buffer的主要属性
        LOGGER.info("position="+intBuffer.position());
        LOGGER.info("limit="+intBuffer.limit());
        LOGGER.info("capacity="+intBuffer.capacity());

    }

}

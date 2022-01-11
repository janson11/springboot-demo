package com.janson.thread.chapter29;

import java.io.IOException;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/9 22:27
 **/
public class RunThrowException {

    /**
     * 普通方法内可以throw  异常，并在方法签名上声明throws
     *
     * @throws Exception
     */
    public void normalMethd() throws Exception {
        throw new IOException();
    }

    Runnable runnable = new Runnable() {
        /**
         *
         *  run 方法上无法声明throws 异常，且ruu方法内无法throw 出checkd Exception，除非使用try catch进行处理。
         */
        @Override
        public void run() {
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
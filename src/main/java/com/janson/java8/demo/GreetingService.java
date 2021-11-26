package com.janson.java8.demo;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2021/11/26 8:51
 **/
@FunctionalInterface
public interface GreetingService {

    /**
     *  say message
     * @param message
     */
    void sayMessage(String message);
}

package com.janson.netty.common;


import org.joda.time.LocalDateTime;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/2/8 2:31 下午
 */
public class Test {
    public static void main(String[] args) {
        LocalDateTime checkTime = LocalDateTime.now();
        LocalDateTime lastExecuteTime = checkTime.withSecondOfMinute(0).withMillisOfSecond(0);
        System.out.println(checkTime.toString());
        System.out.println("------");

        LocalDateTime lastExecuteTime1 = checkTime.minusSeconds(checkTime.getSecondOfMinute());

        System.out.println(lastExecuteTime.toString());
        System.out.println(lastExecuteTime1.toString());

        System.out.println(checkTime.getSecondOfMinute());
        System.out.println(checkTime.getMillisOfSecond());



    }
}

package com.janson.java8.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2021/12/5 18:46
 **/
public class LocalDateTimeDemo {
    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间：" + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1:" + date1);
        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int second = currentTime.getSecond();
        System.out.println("月：" + month + "，日：" + day + "，秒" + second);

        LocalDateTime date2 = currentTime.withDayOfMonth(12).withYear(2021);
        System.out.println("date2:" + date2);
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3:" + date3);

        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4:" + date4);

        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5:" + date5);


        testZoneDateTime();

    }

    public static void testZoneDateTime() {
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1:" + date1);
        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId:" + id);
        ZoneId curentZone = ZoneId.systemDefault();
        System.out.println("当前时区：" + curentZone);

    }
}

package com.janson.service.impl;

import com.janson.service.Person;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/9/2 14:16
 **/
public class Girl implements Person {

    /**
     * 找对象要求
     */
    @Override
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("身高180cm");
        System.out.println("体重73kg");
        System.out.println("家里两套房");
    }
}

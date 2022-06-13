package com.janson.jvm.draw;

import com.janson.jvm.util.PersonManager;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/6/13 10:27 上午
 */
public class Draw {
    public static void main(String[] args) {
        PersonManager personManager = new PersonManager();
        personManager.setInterest(true);
        String personInterest = personManager.getPersonInterest();
        System.out.println(personInterest);
    }
}

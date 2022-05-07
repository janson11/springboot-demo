package com.janson.design.strategy;

import com.janson.design.strategy.behavior.QuackBehavior;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/7 4:39 下午
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<<Silence>>");
    }
}

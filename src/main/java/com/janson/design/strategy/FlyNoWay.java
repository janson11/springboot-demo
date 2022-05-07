package com.janson.design.strategy;

import com.janson.design.strategy.behavior.FlyBehavior;

/**
 * @Description: 给不会飞的鸭子用的飞行行为的实现
 * @Author: shanjian
 * @Date: 2022/5/7 4:34 下午
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}

package com.janson.design.strategy;

import com.janson.design.strategy.behavior.FlyBehavior;

/**
 * @Description: 飞行行为的实现，给真会飞的鸭子用
 * @Author: shanjian
 * @Date: 2022/5/7 4:28 下午
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying!");
    }
}

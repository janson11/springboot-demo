package com.janson.design.strategy;

import com.janson.design.strategy.behavior.FlyBehavior;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/7 5:12 下午
 */
public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket!");
    }
}

package com.janson.design.strategy;

import com.janson.design.strategy.base.Duck;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/7 4:48 下午
 */
public class MallardDuck extends Duck {

    public MallardDuck(){
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}

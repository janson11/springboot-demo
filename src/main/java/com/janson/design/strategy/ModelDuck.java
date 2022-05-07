package com.janson.design.strategy;

import com.janson.design.strategy.base.Duck;

/**
 * @Description: 模型鸭
 * @Author: shanjian
 * @Date: 2022/5/7 4:57 下午
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        // 不会飞
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();

    }

    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }
}

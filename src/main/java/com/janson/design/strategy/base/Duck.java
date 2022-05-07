package com.janson.design.strategy.base;

import com.janson.design.strategy.behavior.FlyBehavior;
import com.janson.design.strategy.behavior.QuackBehavior;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/7 4:41 下午
 */
public abstract class Duck {

    public FlyBehavior flyBehavior;
    public QuackBehavior quackBehavior;

    public Duck() {
    }

    public abstract void display();

    public void performFly() {
        // 委托给行为类
        flyBehavior.fly();
    }


    public void performQuack() {
        // 委托给行为类
        quackBehavior.quack();
    }

    public void swim(){
        System.out.println("All ducks float , even decoys!");
    }

    public void setFlyBehavior(FlyBehavior fb){
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb) {
        quackBehavior =qb;
    }

}

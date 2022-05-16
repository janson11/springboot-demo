package com.janson.design.adapter;

/**
 * @Description: 绿头鸭
 * @Author: shanjian
 * @Date: 2022/5/16 10:48 上午
 */
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}

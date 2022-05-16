package com.janson.design.adapter;

/**
 * @Description: 火鸡的一个具体实现类
 * @Author: shanjian
 * @Date: 2022/5/16 10:52 上午
 */
public class WildTurkey implements Turkey{
    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}

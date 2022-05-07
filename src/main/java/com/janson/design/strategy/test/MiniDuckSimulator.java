package com.janson.design.strategy.test;

import com.janson.design.strategy.MallardDuck;
import com.janson.design.strategy.base.Duck;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/7 4:45 下午
 */
public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();
    }
}

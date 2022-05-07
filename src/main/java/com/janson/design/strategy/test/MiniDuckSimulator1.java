package com.janson.design.strategy.test;

import com.janson.design.strategy.FlyRocketPowered;
import com.janson.design.strategy.MallardDuck;
import com.janson.design.strategy.ModelDuck;
import com.janson.design.strategy.base.Duck;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/7 4:45 下午
 */
public class MiniDuckSimulator1 {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck mode1 = new ModelDuck();
        mode1.performFly();
        mode1.setFlyBehavior(new FlyRocketPowered());
        mode1.performFly();

    }
}

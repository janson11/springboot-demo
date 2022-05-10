package com.janson.design.decorator;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/10 2:53 下午
 */
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    @Override
    public double cost() {
        return .20 + beverage.cost();
    }

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }


}

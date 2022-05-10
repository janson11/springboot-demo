package com.janson.design.decorator;

/**
 * @Description: 调料
 * @Author: shanjian
 * @Date: 2022/5/10 2:43 下午
 */
public abstract class CondimentDecorator extends Beverage {


    @Override
    public abstract String getDescription();

    @Override
    public double cost() {
        return 0;
    }
}

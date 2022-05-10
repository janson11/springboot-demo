package com.janson.design.decorator;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/10 2:50 下午
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}

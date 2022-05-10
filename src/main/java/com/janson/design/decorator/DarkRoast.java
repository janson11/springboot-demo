package com.janson.design.decorator;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/10 4:42 下午
 */
public class DarkRoast extends Beverage{
    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    @Override
    public double cost() {
        return .77;
    }
}

package com.janson.design.decorator;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/10 2:48 下午
 */
public class Espresso extends Beverage{

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}

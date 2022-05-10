package com.janson.design.decorator;

/**
 * @Description: 饮料的基类
 * @Author: shanjian
 * @Date: 2022/5/10 2:40 下午
 */
public abstract class Beverage {

    String description = "Unknow Beverage";

    public String getDescription() {
        return description;
    }

    /**
     * 价格
     * @return
     */
    public abstract double cost();
}

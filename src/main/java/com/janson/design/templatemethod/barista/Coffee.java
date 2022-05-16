package com.janson.design.templatemethod.barista;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/16 4:51 下午
 */
public class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}

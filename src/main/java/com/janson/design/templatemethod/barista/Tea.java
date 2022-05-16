package com.janson.design.templatemethod.barista;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/16 4:50 下午
 */
public class Tea extends CaffeineBeverage{
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Lemon");
    }
}

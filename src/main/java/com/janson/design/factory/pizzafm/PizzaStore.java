package com.janson.design.factory.pizzafm;


/**
 * @Description: 超类
 * @Author: shanjian
 * @Date: 2022/5/11 3:36 下午
 */
public abstract class PizzaStore {

    /**
     * 负责创建pizza订单
     *
     * @param type pizza类型
     * @return
     */
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}

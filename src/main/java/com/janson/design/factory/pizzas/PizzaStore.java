package com.janson.design.factory.pizzas;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/11 3:17 下午
 */
public class PizzaStore {
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }


    public Pizza orderPizza(String type) {
        Pizza pizza =factory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;

    }



}

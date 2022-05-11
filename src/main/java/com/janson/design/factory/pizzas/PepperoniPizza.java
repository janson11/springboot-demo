package com.janson.design.factory.pizzas;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/11 2:13 下午
 */
public class PepperoniPizza extends Pizza{
    public PepperoniPizza() {
        name = "pepperoni Pizza";
        dough = "Crust";
        sauce="Marinara Sauce";
        toppings.add("Sliced pepperoni");
        toppings.add("Grated parmesan cheese");
    }
}

package com.janson.design.factory.pizzas;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/11 2:13 下午
 */
public class CheesePizza extends Pizza{
    public CheesePizza() {
        name = "Cheese Pizza";
        dough = "Regular Crust";
        sauce="Marinara Pizza Sauce";
        toppings.add("Fresh Mozzarella");
        toppings.add("Parmesan");
    }
}

package com.janson.design.factory.pizzas;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/11 2:13 下午
 */
public class ClamPizza extends Pizza{
    public ClamPizza() {
        name = "Clam Pizza";
        dough = "Thin Crust";
        sauce="White Pizza Sauce";
        toppings.add("Clam");
        toppings.add("Grated parmesan cheese");
    }
}

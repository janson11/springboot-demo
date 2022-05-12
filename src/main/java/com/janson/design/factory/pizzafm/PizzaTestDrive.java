package com.janson.design.factory.pizzafm;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/11 4:52 下午
 */
public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a "+pizza.getName() +"\n");

    }
}

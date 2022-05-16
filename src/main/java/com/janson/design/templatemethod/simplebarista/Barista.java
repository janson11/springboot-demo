package com.janson.design.templatemethod.simplebarista;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/16 4:01 下午
 */
public class Barista {
    public static void main(String[] args) {
        Tea tea = new Tea();
        Coffee coffee = new Coffee();
        System.out.println("Making tea...");
        tea.prepareRecipe();

        System.out.println("Making coffee...");
        coffee.prepareRecipe();

    }
}

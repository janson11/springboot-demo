package com.janson.design.templatemethod.barista;


/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/16 4:48 下午
 */
public class BeverageTestDrive {

    public static void main(String[] args) {
        Tea tea = new Tea();
        Coffee coffee = new Coffee();

        System.out.println("Making tea...");
        tea.prepareRecipe();

        System.out.println("Making coffee...");
        coffee.prepareRecipe();

        TeaWithHook teaWithHook = new TeaWithHook();
        CoffeeWithHook coffeeWithHook = new CoffeeWithHook();
        System.out.println("Making tea 1...");
        teaWithHook.prepareRecipe();

        System.out.println("Making coffee 1 ...");
        coffeeWithHook.prepareRecipe();
    }
}

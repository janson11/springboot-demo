package com.janson.design.templatemethod.simplebarista;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/16 3:32 下午
 */
public class Coffee {

    public void prepareRecipe() {
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugarAndMilk();

    }

    /**
     * 把水煮沸
     */
    public void boilWater() {
        System.out.println("Boiling water");
    }

    /**
     * 用沸水冲泡咖啡粉
     */
    public void brewCoffeeGrinds() {
        System.out.println("Dripping Coffee through filter");
    }

    /**
     * 把咖啡倒进被子
     */
    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    /**
     * 加糖和牛奶
     */
    public void addSugarAndMilk() {
        System.out.println("Adding Sugar and Milk");
    }

}

package com.janson.design.templatemethod.simplebarista;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/16 3:32 下午
 */
public class Tea {

    public void prepareRecipe() {
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();

    }

    /**
     * 把水煮沸
     */
    public void boilWater() {
        System.out.println("Boiling water");
    }

    /**
     * 用沸水浸泡茶叶
     */
    public void steepTeaBag() {
        System.out.println("Steeping the tea");
    }

    /**
     * 把茶倒进杯子
     */
    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    /**
     * 加柠檬
     */
    public void addLemon() {
        System.out.println("Adding Lemon");
    }

}

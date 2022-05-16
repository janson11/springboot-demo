package com.janson.design.templatemethod.barista;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/16 4:23 下午
 */
public abstract class CaffeineBeverageWithHook {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater() {
        System.out.println("Boiling water");
    }


    void pourInCup() {
        System.out.println("Pouring into cup");
    }

    /**
     * 这是一个钩子
     *
     * @return
     */
    boolean customerWantsCondiments() {
        return true;
    }
}

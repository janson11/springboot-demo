package com.janson.design.factory.pizzaaf;

/**
 * @Description: pizza 原料工厂
 * @Author: shanjian
 * @Date: 2022/5/11 5:27 下午
 */
public interface PizzaIngredientFactory {

    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClam();

}

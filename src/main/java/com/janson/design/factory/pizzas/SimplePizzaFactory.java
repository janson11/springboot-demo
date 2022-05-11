package com.janson.design.factory.pizzas;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/11 2:34 下午
 */
public class SimplePizzaFactory {


    public Pizza createPizza(String type){
        Pizza pizza = null;
        if (type.equals("cheese")){
            pizza = new CheesePizza();
        } else if (type.equals("pepperoni")){
            pizza = new PepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new ClamPizza();
        } else if (type.equals("veggie")){
            pizza = new VeggiePizza();
        }
/*        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();*/

        return pizza;


    }

}
package com.janson.design.decorator;

/**
 * @Description: 饮料的基类
 * @Author: shanjian
 * @Date: 2022/5/10 2:40 下午
 */
public abstract class Beverage {

    public static final int TALL = 0;
    public static final int GRANDE = 1;
    public static final int VENTI = 2;

    String description = "Unknow Beverage";

    public String getDescription() {
        return description;
    }

    /**
     * 价格
     * @return
     */
    public abstract double cost();


    public int getSize(){
        return 0;
    }

}

package com.janson.java8.demo.reference;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2021/11/29 9:28
 **/
public class Car implements Vechicle, FourWheeler {

/*    default void print() {
        System.out.println("我是一辆四轮汽车！");
    }*/


    public void print() {
        Vechicle.super.print();
        FourWheeler.super.print();
        Vechicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }

}

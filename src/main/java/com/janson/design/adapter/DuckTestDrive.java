package com.janson.design.adapter;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/16 11:32 上午
 */
public class DuckTestDrive {

    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();
        WildTurkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        System.out.println("The Turkey says...");
        turkey.gobble();
        turkey.fly();

        System.out.println("The Duck says...");
        testDuck(duck);
        System.out.println("The TurkeyAdapter says...");
        testDuck(turkeyAdapter);
    }

    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}

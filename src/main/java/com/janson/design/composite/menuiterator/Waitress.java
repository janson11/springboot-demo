package com.janson.design.composite.menuiterator;

import java.util.Iterator;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/19 10:42 上午
 */
public class Waitress {

    MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        allMenus.print();
    }

    public void printVegetarianMenu() {
        Iterator<MenuComponent> iterator = allMenus.createIterator();

        System.out.println("VEGETAIAN MENU------");
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            try {

                if (menuComponent.isVegetarian()) {
                    menuComponent.print();
                }
            } catch (UnsupportedOperationException e) {

            }
        }
    }


}

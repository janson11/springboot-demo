package com.janson.design.composite.menuiterator;

import java.util.Iterator;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/18 10:57 上午
 */
public class NullIterator implements Iterator<MenuComponent> {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public MenuComponent next() {
        return null;
    }
}

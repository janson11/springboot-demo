package com.janson.design.observer.custom;

/**
 * @Description: 主题接口
 * @Author: shanjian
 * @Date: 2022/5/9 10:49 上午
 */
public interface Subject {
    /**
     * 注册观察者
     * @param o
     */
    public void registerObserver(Observer o);

    /**
     * 注销观察者
     * @param o
     */
    public void removeObserver(Observer o);

    /**
     * 当主题状态改变时，主题会把这些状态值当作方法的参数，传送给观察者
     */
    public void notifyObservers();
}

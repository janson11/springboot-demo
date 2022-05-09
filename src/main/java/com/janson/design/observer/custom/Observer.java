package com.janson.design.observer.custom;

/**
 * @Description: 观察者接口
 * @Author: shanjian
 * @Date: 2022/5/9 10:49 上午
 */
public interface Observer {
    /**
     * 更新气象
     *
     * @param temp 温度
      * @param humidity 湿度
     * @param pressure 气压
     */
    public void update(float temp, float humidity, float pressure);
}

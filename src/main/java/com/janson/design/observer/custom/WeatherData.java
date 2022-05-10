package com.janson.design.observer.custom;

import java.util.ArrayList;

/**
 * @Description: 天气数据实现了主题接口
 * @Author: shanjian
 * @Date: 2022/5/9 11:33 上午
 */
public class WeatherData implements Subject {

    /**
     * 记录观察者
     */
    private ArrayList observers;
    /**
     * 温度
     */
    private float temperature;
    /**
     * 湿度
     */
    private float humidity;
    /**
     * 压力
     */
    private float pressure;

    public WeatherData() {
        observers = new ArrayList();
    }


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);

    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer o = (Observer) observers.get(i);
            o.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }


}

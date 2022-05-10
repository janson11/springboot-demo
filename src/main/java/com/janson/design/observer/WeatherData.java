package com.janson.design.observer;

import java.util.Observable;

/**
 * @Description: 利用java内置的可观察的【主题】
 * @Author: shanjian
 * @Date: 2022/5/10 10:12 上午
 */
public class WeatherData extends Observable {
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
    }


    public void measurementsChanged() {
        setChanged();
        // 拉数据
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

package com.janson.design.observer;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/9 5:34 下午
 */
public class WeatherStation {

    public static void main(String[] args) {
        com.janson.design.observer.WeatherData weatherData = new com.janson.design.observer.WeatherData();
        com.janson.design.observer.CurrentConditionsDisplay currentConditionsDisplay = new com.janson.design.observer.CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.1f);
    }
}

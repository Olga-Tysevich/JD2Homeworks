package org.example.patterns.observer.example_from_book.oldV.displays;

import org.example.patterns.observer.example_from_book.oldV.WeatherData;

import java.util.Observable;
import java.util.Observer;

import static org.example.patterns.observer.example_from_book.oldV.Constants.STATISTIC;

public class StatisticsDisplay implements Observer, DisplayElement {
    private Observable observable;
    private double temperature;
    private double maxTemperature;
    private double minTemperature;
    private double avgTemperature;
    private double humidity;

    public StatisticsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void display() {
        System.out.printf(STATISTIC, avgTemperature, maxTemperature, minTemperature);
    }


    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData= (WeatherData) obs;
            this.maxTemperature = Math.max(this.temperature, weatherData.getTemperature());
            this.minTemperature = Math.min(this.temperature, weatherData.getTemperature());
            this.avgTemperature = ((this.temperature + weatherData.getTemperature()) / 2);
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}

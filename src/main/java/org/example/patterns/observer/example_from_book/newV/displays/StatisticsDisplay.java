package org.example.patterns.observer.example_from_book.newV.displays;

import org.example.patterns.observer.example_from_book.newV.WeatherData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.example.patterns.observer.example_from_book.newV.Constants.STATISTIC;
import static org.example.patterns.observer.example_from_book.newV.Constants.TEMPERATURE;

public class StatisticsDisplay implements PropertyChangeListener {
    private double temperature;
    private double maxTemperature;
    private double minTemperature;
    private double avgTemperature;

    public StatisticsDisplay(WeatherData weatherData) {
        weatherData.addListener(this);
    }

    public void display() {
        System.out.printf(STATISTIC, avgTemperature, maxTemperature, minTemperature);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TEMPERATURE.equals(evt.getPropertyName())) {
            double newTemperature = (double) evt.getNewValue();
            this.maxTemperature = Math.max(this.temperature, newTemperature);
            this.minTemperature = Math.min(this.temperature, newTemperature);
            this.avgTemperature = ((this.temperature + newTemperature) / 2);
            this.temperature = newTemperature;
        }
        display();
    }
}

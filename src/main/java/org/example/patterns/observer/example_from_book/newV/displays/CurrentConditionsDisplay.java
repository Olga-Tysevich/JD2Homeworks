package org.example.patterns.observer.example_from_book.newV.displays;

import org.example.patterns.observer.example_from_book.newV.WeatherData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.example.patterns.observer.example_from_book.newV.Constants.*;

public class CurrentConditionsDisplay implements PropertyChangeListener {
    private double temperature;
    private double humidity;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        weatherData.addListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt) {

        if (TEMPERATURE.equals(evt.getPropertyName())) {
            this.temperature = ((double) evt.getNewValue());
        } else if (HUMIDITY.equals(evt.getPropertyName())) {
            this.humidity = ((double) evt.getNewValue());
        }
        display();
    }

    public void display() {
        System.out.printf(CURRENT_CONDITIONS, temperature, humidity);
    }
}

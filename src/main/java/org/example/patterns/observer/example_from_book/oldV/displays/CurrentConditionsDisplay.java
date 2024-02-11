package org.example.patterns.observer.example_from_book.oldV.displays;

import org.example.patterns.observer.example_from_book.oldV.WeatherData;

import java.util.Observable;
import java.util.Observer;

import static org.example.patterns.observer.example_from_book.oldV.Constants.CURRENT_CONDITIONS;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private Observable observable;
    private double temperature;
    private double humidity;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

    public void display() {
        System.out.printf(CURRENT_CONDITIONS, temperature, humidity);
    }
}

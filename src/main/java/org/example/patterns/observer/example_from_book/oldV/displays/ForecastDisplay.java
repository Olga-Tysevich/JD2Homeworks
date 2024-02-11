package org.example.patterns.observer.example_from_book.oldV.displays;

import org.example.patterns.observer.example_from_book.oldV.WeatherData;

import java.util.Observable;
import java.util.Observer;

import static org.example.patterns.observer.example_from_book.oldV.Constants.*;

public class ForecastDisplay implements Observer, DisplayElement {
    private Observable observable;
    private double currentPressure;
    private double lastPressure;
    private double currentTemperature;
    private double lastTemperature;
    private String message;

    public ForecastDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    public void display() {
        System.out.printf(FORECAST, message);
    }


    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.lastPressure = currentPressure;
            this.currentPressure = weatherData.getPressure();
            this.lastTemperature = currentTemperature;
            this.currentTemperature = weatherData.getTemperature();
            if (lastTemperature > currentTemperature) {
                message = FORECAST_POSITIVE_MESSAGE;
            } else if (lastTemperature < currentTemperature) {
                message = FORECAST_NEGATIVE_MESSAGE;
            } else {
                message = FORECAST_WEATHER_THE_SAME_MESSAGE;
            }
            display();
        }
    }
}

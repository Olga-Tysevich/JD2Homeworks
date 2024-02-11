package org.example.patterns.observer.example_from_book.newV.displays;

import org.example.patterns.observer.example_from_book.newV.WeatherData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.example.patterns.observer.example_from_book.newV.Constants.*;

public class ForecastDisplay implements PropertyChangeListener {
    private double currentPressure;
    private double lastPressure;
    private double currentTemperature;
    private double lastTemperature;
    private String message;

    public ForecastDisplay(WeatherData weatherData) {
        weatherData.addListener(this);
    }

    public void display() {
        System.out.printf(FORECAST, message);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TEMPERATURE.equals(evt.getPropertyName())) {
            this.lastTemperature = currentTemperature;
            this.currentTemperature = ((double) evt.getNewValue());
        } else if (PRESSURE.equals(evt.getPropertyName())) {
            this.lastPressure = currentPressure;
            this.currentPressure = ((double) evt.getNewValue());
        }
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

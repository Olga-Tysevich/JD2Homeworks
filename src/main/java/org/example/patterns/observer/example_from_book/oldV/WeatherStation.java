package org.example.patterns.observer.example_from_book.oldV;

import org.example.patterns.observer.example_from_book.oldV.displays.CurrentConditionsDisplay;
import org.example.patterns.observer.example_from_book.oldV.displays.ForecastDisplay;
import org.example.patterns.observer.example_from_book.oldV.displays.StatisticsDisplay;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay =
                new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}

package org.example.patterns.observer.example_from_book.newV;


import org.example.patterns.observer.example_from_book.newV.displays.CurrentConditionsDisplay;
import org.example.patterns.observer.example_from_book.newV.displays.ForecastDisplay;
import org.example.patterns.observer.example_from_book.newV.displays.StatisticsDisplay;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay =
                new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setTemperature(80);
        weatherData.setPressure(70);
        weatherData.setHumidity(29.2);

    }
}

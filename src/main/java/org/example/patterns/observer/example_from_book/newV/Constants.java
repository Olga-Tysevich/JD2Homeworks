package org.example.patterns.observer.example_from_book.newV;

public class Constants {
    //All
    public static final String TEMPERATURE = "temperature";
    public static final String HUMIDITY = "humidity";
    public static final String PRESSURE = "pressure";
    //displays.CurrentConditionsDisplay
    public static final String CURRENT_CONDITIONS = "Current conditions: %.2f F degrees and %.2f humidity\n";
    //displays.StatisticsDisplay
    public static final String STATISTIC = "%.2f/%.2f/%.2f temperature = 80.0/80.0/80.0\n";
    //displays.ForecastDisplay
    public static final String FORECAST = "Forecast: %s\n";
    public static final String FORECAST_POSITIVE_MESSAGE = "Improving weather on the way!";
    public static final String FORECAST_NEGATIVE_MESSAGE = "The weather is getting worse";
    public static final String FORECAST_WEATHER_THE_SAME_MESSAGE = "The weather hasn't changed";

    private Constants() {
    }
}

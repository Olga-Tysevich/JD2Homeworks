package org.example.patterns.observer.example_from_book.newV;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.example.patterns.observer.example_from_book.newV.Constants.*;

public class WeatherData {
    private double temperature;
    private double humidity;
    private double pressure;
    private PropertyChangeSupport support;


    public WeatherData() {
        support = new PropertyChangeSupport(this);
    }

    public void addListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setTemperature(double temperature) {
        support.firePropertyChange(TEMPERATURE, this.temperature, temperature);
    }

    public void setHumidity(double humidity) {
        support.firePropertyChange(HUMIDITY, this.humidity, humidity);
    }

    public void setPressure(double pressure) {
        support.firePropertyChange(PRESSURE, this.pressure, pressure);
    }

}

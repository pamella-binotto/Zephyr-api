package com.zephyr.api.dto;

public class WeatherDataRequestDTO {

    private double temperature;
    private double humidity;

    public WeatherDataRequestDTO() {

    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}

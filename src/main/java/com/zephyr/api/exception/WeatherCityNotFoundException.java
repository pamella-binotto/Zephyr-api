package com.zephyr.api.exception;

public class WeatherCityNotFoundException extends RuntimeException {
    public WeatherCityNotFoundException() {
    }
    public WeatherCityNotFoundException(String message) {
        super(message);
    }
}

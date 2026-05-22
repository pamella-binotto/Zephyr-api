package com.zephyr.api.exception;

public class WeatherDataNotFoundException extends RuntimeException {

    public WeatherDataNotFoundException(String message) {
        super(message);
    }
}

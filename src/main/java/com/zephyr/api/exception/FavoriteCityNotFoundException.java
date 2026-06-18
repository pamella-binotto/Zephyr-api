package com.zephyr.api.exception;

public class FavoriteCityNotFoundException extends RuntimeException {
    public FavoriteCityNotFoundException(String message) {
        super(message);
    }
}

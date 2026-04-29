package com.zephyr.api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WeatherData {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private Double temperature;
    private Double humidity;
}

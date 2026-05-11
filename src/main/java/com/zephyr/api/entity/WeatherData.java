package com.zephyr.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WeatherData {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperature;
    private Double humidity;

    public WeatherData () {

    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}


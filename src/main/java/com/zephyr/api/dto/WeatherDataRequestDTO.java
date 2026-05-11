package com.zephyr.api.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;


public class WeatherDataRequestDTO {

    @NotNull
    @Min(-100)
    @Max(100)
    private Double temperature;

    @NotNull
    @Min(0)
    @Max(100)
    private Double humidity;

    public WeatherDataRequestDTO() {

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
}

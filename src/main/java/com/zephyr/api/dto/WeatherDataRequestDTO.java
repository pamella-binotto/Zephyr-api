package com.zephyr.api.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;


public class WeatherDataRequestDTO {

    @NotNull(message = "Temperature is required")
    @Min(value = -100, message = "Temperature must be at least -100")
    @Max( value = 100, message = "Temperature must be less than or equal to 100")
    private Double temperature;

    @NotNull(message = "Humidity is required")
    @Min(value = 0, message = "Humidity must be at least 0")
    @Max(value = 100, message = "Humidity must be less than or equal to 100" )
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

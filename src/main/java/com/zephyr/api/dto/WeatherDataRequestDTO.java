package com.zephyr.api.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;


public class WeatherDataRequestDTO {

    @Schema (
            description = "Temperature in Celsius",
            example = "25.5"
    )
    @NotNull(message = "Temperature is required")
    @Min(value = -100, message = "Temperature must be at least -100")
    @Max( value = 100, message = "Temperature must be less than or equal to 100")
    private Double temperature;


    @Schema(
            description = "Relative air humidity percentage",
            example = "65.0"
    )
    @NotNull(message = "Humidity is required")
    @Min(value = 0, message = "Humidity must be at least 0")
    @Max(value = 100, message = "Humidity must be less than or equal to 100" )
    private Double humidity;


    @Schema(
            description = "Name of city",
            example = "Florianópolis"

    )
    @NotBlank(message = "City is required")
    private String city;

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

    public WeatherDataRequestDTO(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

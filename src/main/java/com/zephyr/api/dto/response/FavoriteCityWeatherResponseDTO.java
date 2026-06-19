package com.zephyr.api.dto.response;

public class FavoriteCityWeatherResponseDTO {

    private String city;
    private Double temperature;
    private Double humidity;
    private Double windSpeed;
    private String windAlert;

    public FavoriteCityWeatherResponseDTO() {}

    public FavoriteCityWeatherResponseDTO(String city, Double temperature,
                                          Double humidity, Double windSpeed,
                                          String windAlert) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windAlert = windAlert;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindAlert() {
        return windAlert;
    }

    public void setWindAlert(String windAlert) {
        this.windAlert = windAlert;
    }
}

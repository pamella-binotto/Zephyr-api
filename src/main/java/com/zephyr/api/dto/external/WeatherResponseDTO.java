package com.zephyr.api.dto.external;

public class WeatherResponseDTO {

    private MainWeatherDTO main;

    public MainWeatherDTO getMain() {
        return main;
    }

    public void setMain(MainWeatherDTO main) {
        this.main = main;
    }
}

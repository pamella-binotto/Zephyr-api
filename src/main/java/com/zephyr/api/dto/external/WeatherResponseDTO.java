package com.zephyr.api.dto.external;

public class WeatherResponseDTO {

    private MainWeatherDTO main;

    public MainWeatherDTO getMain() {
        return main;
    }

    public void setMain(MainWeatherDTO main) {
        this.main = main;
    }

    private WindDTO wind;

    public WindDTO getWind() {
        return wind;
    }

    public void setWind(WindDTO wind) {
        this.wind = wind;
    }

}

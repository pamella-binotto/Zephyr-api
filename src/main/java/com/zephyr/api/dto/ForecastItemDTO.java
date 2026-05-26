package com.zephyr.api.dto;

import com.zephyr.api.dto.external.MainWeatherDTO;
import com.zephyr.api.dto.external.WindDTO;

public class ForecastItemDTO {

    private MainWeatherDTO main;
    private WindDTO wind;
    private String dt_txt;


    public ForecastItemDTO(MainWeatherDTO main, WindDTO wind, String dt_txt) {
        this.main = main;
        this.wind = wind;
        this.dt_txt = dt_txt;
    }

    public MainWeatherDTO getMain() {
        return main;
    }

    public void setMain(MainWeatherDTO main) {
        this.main = main;
    }

    public WindDTO getWind() {
        return wind;
    }

    public void setWind(WindDTO wind) {
        this.wind = wind;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}

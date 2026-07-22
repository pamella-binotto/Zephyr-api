package com.zephyr.api.dto;

import com.zephyr.api.dto.external.MainWeatherDTO;
import com.zephyr.api.dto.external.WeatherConditionDTO;
import com.zephyr.api.dto.external.WindDTO;

import java.util.List;

public class ForecastItemDTO {

    private MainWeatherDTO main;
    private WindDTO wind;
    private String dt_txt;
    private Double pop;
    private List<WeatherConditionDTO> weather;


    public ForecastItemDTO(MainWeatherDTO main, WindDTO wind, String dt_txt,
                           Double pop, List<WeatherConditionDTO> weather) {
        this.main = main;
        this.wind = wind;
        this.dt_txt = dt_txt;
        this.pop = pop;
        this.weather = weather;
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

    public Double getPop() {
        return pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
    }

    public List<WeatherConditionDTO> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherConditionDTO> weather) {
        this.weather = weather;
    }
}

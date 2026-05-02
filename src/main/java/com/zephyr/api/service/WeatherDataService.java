package com.zephyr.api.service;


import com.zephyr.api.WeatherData;
import com.zephyr.api.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataService {

    private final WeatherDataRepository repository;

    public WeatherDataService(WeatherDataRepository repository) {
        this.repository = repository;
    }


    public WeatherData save(WeatherData weatherData){
        return repository.save(weatherData);
    }
}

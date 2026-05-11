package com.zephyr.api.service;


import com.zephyr.api.dto.WeatherDataRequestDTO;
import com.zephyr.api.entity.WeatherData;
import com.zephyr.api.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherDataService {

    private final WeatherDataRepository repository;

    public WeatherDataService(WeatherDataRepository repository) {
        this.repository = repository;
    }


    public WeatherData save(WeatherData weatherData){
        return repository.save(weatherData);
    }

    public WeatherData save(WeatherDataRequestDTO dto){

        WeatherData weatherData = new WeatherData();

        weatherData.setTemperature(dto.getTemperature());
        weatherData.setHumidity(dto.getHumidity());

        return repository.save(weatherData);
    }

    public List<WeatherData> findAll() {
        return repository.findAll();
    }

    public WeatherData findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Weather Data Not Found."));
    }

    public void delete(Long id){
        WeatherData weatherData = repository.findById(id).
                orElseThrow(() -> new RuntimeException("Weather Data Not Found."));
    }


}

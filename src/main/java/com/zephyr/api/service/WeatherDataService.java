package com.zephyr.api.service;


import com.zephyr.api.client.WeatherApiClient;
import com.zephyr.api.dto.WeatherDataRequestDTO;
import com.zephyr.api.dto.external.WeatherResponseDTO;
import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.entity.WeatherData;
import com.zephyr.api.exception.WeatherDataNotFoundException;
import com.zephyr.api.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherDataService {

    private final WeatherDataRepository repository;
    private final WeatherApiClient apiClient;


    public WeatherDataService(WeatherDataRepository repository, WeatherApiClient apiClient) {
        this.repository = repository;
        this.apiClient = apiClient;
    }


    public WeatherData save(WeatherData weatherData) {
        return repository.save(weatherData);
    }

    public WeatherData save(WeatherDataRequestDTO dto) {

        WeatherData weatherData = new WeatherData();

        weatherData.setTemperature(dto.getTemperature());
        weatherData.setHumidity(dto.getHumidity());

        return repository.save(weatherData);
    }


    public List<WeatherData> findAll() {
        return repository.findAll();
    }

    public WeatherData findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new WeatherDataNotFoundException("Weather Data Not Found."));
    }

    public void delete(Long id) {
        WeatherData weatherData = repository.findById(id).
                orElseThrow(() -> new WeatherDataNotFoundException("Weather Data Not Found."));
        repository.delete(weatherData);
    }

    public CurrentWeatherResponseDTO getCurrentWeather(String city) {

        WeatherResponseDTO response = apiClient.getWeatherByCity(city);

        Double windSpeedKm = response.getWind().getSpeed()*3.6;

        WeatherData weatherData = new WeatherData();

        weatherData.setTemperature(response.getMain().getTemp());
        weatherData.setHumidity(response.getMain().getHumidity());

        repository.save(weatherData);


        return new CurrentWeatherResponseDTO(
                city,
                response.getMain().getTemp(),
                response.getMain().getHumidity(),
                windSpeedKm

        );



    }


}

package com.zephyr.api.service;


import com.zephyr.api.client.WeatherApiClient;
import com.zephyr.api.dto.ForecastItemDTO;
import com.zephyr.api.dto.WeatherDataRequestDTO;
import com.zephyr.api.dto.external.WeatherResponseDTO;
import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.dto.response.ForecastDayResponseDTO;
import com.zephyr.api.dto.response.ForecastResponseDTO;
import com.zephyr.api.entity.WeatherData;
import com.zephyr.api.exception.WeatherDataNotFoundException;
import com.zephyr.api.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
        weatherData.setCity(dto.getCity());


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

        Double windSpeedKm = (double) Math.round(response.getWind().getSpeed() * 3.6);

        String alert;

        if (windSpeedKm >= 80) {
            alert = "Alerta severo de ventos fortes";
        } else if (windSpeedKm >= 60) {
            alert = "Evite deslocamentos de moto ou bicicleta.";
        } else if (windSpeedKm >= 40) {
            alert = "Ventos fortes no dia de hoje";
        } else {
            alert = "Condições climáticas estáveis.";
        }


        WeatherData weatherData = new WeatherData();

        weatherData.setTemperature(response.getMain().getTemp());
        weatherData.setHumidity(response.getMain().getHumidity());
        weatherData.setCity(city);


        repository.save(weatherData);


        return new CurrentWeatherResponseDTO(
                city,
                response.getMain().getTemp(),
                response.getMain().getHumidity(),
                windSpeedKm,
                alert

        );

    }

    public List<ForecastDayResponseDTO> getForecast(String city) {

        ForecastResponseDTO response =
                apiClient.getForecastByCity(city);

        Map<String, ForecastDayResponseDTO> forecastMap = new HashMap<>();


        for (
                ForecastItemDTO item : response.getList()) {
            String date = item.getDt_txt().split(" ")[0];

            Double windSpeedKm =
                    (double) Math.round(item.getWind().getSpeed() * 3.6);

            String alert;


            if (windSpeedKm >= 80) {
                alert = "Alerta severo de ventos fortes";
            } else if (windSpeedKm >= 60) {
                alert = "Evite deslocamentos de moto ou bicicleta.";
            } else if (windSpeedKm >= 40) {
                alert = "Ventos fortes no dia de hoje";
            } else {
                alert = "Condições climáticas estáveis.";
            }

            if (!forecastMap.containsKey(date)) {

                ForecastDayResponseDTO dto =
                        new ForecastDayResponseDTO(

                                date,
                                item.getMain().getHumidity(),
                                windSpeedKm,
                                alert,
                                item.getMain().getTemp_min(),
                                item.getMain().getTemp_max()

                        );

                forecastMap.put(date, dto);
            } else {
                ForecastDayResponseDTO existingForecast =
                        forecastMap.get(date);

                existingForecast.setMinTemp(
                        Math.min(
                                existingForecast.getMinTemp(),
                                item.getMain().getTemp_min()
                        )
                );

                existingForecast.setMaxTemp(
                        Math.max(
                                existingForecast.getMaxTemp(),
                                item.getMain().getTemp_max()
                        )
                );
            }
        }
        return new ArrayList<>(forecastMap.values());

    }


}

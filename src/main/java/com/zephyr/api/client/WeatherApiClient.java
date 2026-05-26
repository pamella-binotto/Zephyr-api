package com.zephyr.api.client;

import com.zephyr.api.dto.external.WeatherResponseDTO;
import com.zephyr.api.dto.response.ForecastResponseDTO;
import com.zephyr.api.exception.WeatherCityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class WeatherApiClient {

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponseDTO getWeatherByCity(String city) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&appid="
                + apiKey
                + "&units=metric";

        try {

            return restTemplate.getForObject(url, WeatherResponseDTO.class);

        } catch (HttpClientErrorException ex) {

            throw new WeatherCityNotFoundException(
                    String.format("City not found: %s", city)
            );
        }

    }

    public ForecastResponseDTO getForecastByCity(String city) {

        String url = "https://api.openweathermap.org/data/2.5/forecast?q="
                + city
                +"&appid="
                +apiKey
                +"&units=metric";

        try {

            return restTemplate.getForObject(url, ForecastResponseDTO.class);

        } catch (HttpClientErrorException ex) {

            throw new WeatherCityNotFoundException(
                    String.format("City not found: %s", city)
            );
        }

    }
}
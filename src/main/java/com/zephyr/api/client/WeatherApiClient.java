package com.zephyr.api.client;

import com.zephyr.api.dto.external.WeatherResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiClient {

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();


    public WeatherResponseDTO getWeather(String city) {

        String url =  "https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&appid="
                + apiKey
                + "&units=metric";

        return restTemplate.getForObject(url, WeatherResponseDTO.class);

    }
}

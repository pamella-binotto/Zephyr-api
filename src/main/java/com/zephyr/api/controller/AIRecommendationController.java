package com.zephyr.api.controller;

import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.dto.response.IARecommendationResponseDTO;
import com.zephyr.api.service.AIRecommendationService;
import com.zephyr.api.service.WeatherDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIRecommendationController {

    private final AIRecommendationService aiRecommendationService;
    private final WeatherDataService weatherDataService;

    public AIRecommendationController(AIRecommendationService aiRecommendationService,
                                      WeatherDataService weatherDataService) {
        this.aiRecommendationService = aiRecommendationService;
        this.weatherDataService = weatherDataService;
    }


    @GetMapping("/recommendation/{city}")
    public ResponseEntity<IARecommendationResponseDTO> getRecommendation(@PathVariable String city) {

        CurrentWeatherResponseDTO weather =
                weatherDataService.getCurrentWeather(city);

        String recommendation =
                aiRecommendationService.generateRecommendation(weather);

        return ResponseEntity.ok(
                new IARecommendationResponseDTO(recommendation)
        );
    }


}


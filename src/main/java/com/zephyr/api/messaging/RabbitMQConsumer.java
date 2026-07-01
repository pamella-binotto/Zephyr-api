package com.zephyr.api.messaging;

import com.zephyr.api.config.RabbitMQConfig;
import com.zephyr.api.dto.FavoriteCityEventDTO;
import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.service.AIRecommendationService;
import com.zephyr.api.service.WeatherDataService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

   private final WeatherDataService weatherDataService;
   private final AIRecommendationService aiRecommendationService;

    public RabbitMQConsumer(WeatherDataService weatherDataService,
                            AIRecommendationService aiRecommendationService) {
        this.weatherDataService = weatherDataService;
        this.aiRecommendationService = aiRecommendationService;
    }

    @RabbitListener(queues = RabbitMQConfig.FAVORITE_CITY_QUEUE)
    public void receiveMessage(FavoriteCityEventDTO event){
        System.out.println("Message received: ");
        System.out.println(event);

        CurrentWeatherResponseDTO weather =
                weatherDataService.getCurrentWeather(event.getCityName());

        String recommendation = aiRecommendationService.generateRecommendation(weather);
        System.out.println(recommendation);

    }
}

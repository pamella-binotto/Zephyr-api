package com.zephyr.api.service;

import com.zephyr.api.client.AIClient;
import com.zephyr.api.dto.ai.AIResponseDTO;
import com.zephyr.api.dto.ai.ChoiceDTO;
import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.prompt.WeatherPromptBuilder;
import org.springframework.stereotype.Service;

@Service
public class AIRecommendationService {

    private final AIClient aiClient;
    private final WeatherPromptBuilder promptBuilder;

    public AIRecommendationService(AIClient aiClient,
                                   WeatherPromptBuilder promptBuilder) {
        this.aiClient = aiClient;
        this.promptBuilder = promptBuilder;
    }

    public String generateRecommendation(CurrentWeatherResponseDTO weather) {

        String prompt = promptBuilder.build(weather);

        AIResponseDTO response =
                aiClient.sendPrompt(prompt);

        ChoiceDTO choice = response.getChoices().get(0);
        return choice.getMessage().getContent();


    }

}


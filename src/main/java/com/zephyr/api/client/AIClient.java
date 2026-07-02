package com.zephyr.api.client;

import com.zephyr.api.dto.ai.AIRequestDTO;
import com.zephyr.api.dto.ai.AIResponseDTO;
import com.zephyr.api.dto.ai.MessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AIClient {

    private final RestTemplate restTemplate;

    public AIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${ai.base-url}")
    private String baseUrl;

    @Value("${ai.api-key}")
    private String apiKey;

    @Value("${ai.model}")
    private String model;

   public AIResponseDTO sendPrompt(String prompt) {

       String url = baseUrl + "/chat/completions";

       MessageDTO message = new MessageDTO(
               "user",
               prompt
       );

       AIRequestDTO request = new AIRequestDTO(
               model,
               List.of(message)
       );

       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setBearerAuth(apiKey);

       HttpEntity<AIRequestDTO> entity =
               new HttpEntity<>(request, headers);


       ResponseEntity<AIResponseDTO> response =
               restTemplate.exchange(url,
                       HttpMethod.POST,
                       entity,
                       AIResponseDTO.class);


       return response.getBody();
   }

}

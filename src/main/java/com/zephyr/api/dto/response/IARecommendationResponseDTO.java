package com.zephyr.api.dto.response;

public class IARecommendationResponseDTO {

    private String recommendation;

    public IARecommendationResponseDTO(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}

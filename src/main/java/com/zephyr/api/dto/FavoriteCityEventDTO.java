package com.zephyr.api.dto;

import java.time.LocalDateTime;

public class FavoriteCityEventDTO {

    private Long cityId;
    private String cityName;
    private String userEmail;
    private LocalDateTime createdAt;

    public FavoriteCityEventDTO() {}

    public FavoriteCityEventDTO(Long cityId, String cityName,
                                String userEmail, LocalDateTime createdAt) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "FavoriteCityEventDTO{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

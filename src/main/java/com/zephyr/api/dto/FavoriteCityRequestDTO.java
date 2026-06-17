package com.zephyr.api.dto;

import jakarta.validation.constraints.NotBlank;

public class FavoriteCityRequestDTO {

    @NotBlank (message = "City name is required")
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

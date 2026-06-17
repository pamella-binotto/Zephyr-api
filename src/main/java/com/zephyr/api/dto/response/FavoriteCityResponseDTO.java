package com.zephyr.api.dto.response;

public class FavoriteCityResponseDTO {

    private Long id;
    private String cityName;

    public FavoriteCityResponseDTO(Long id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

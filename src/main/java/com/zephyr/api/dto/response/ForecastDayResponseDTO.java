package com.zephyr.api.dto.response;


public class ForecastDayResponseDTO {

    private String date;
    private Double humidity;

    private Double windSpeed;
    private Double maxWindSpeed;

    private String alert;

    private Double minTemp;
    private Double maxTemp;



    public ForecastDayResponseDTO(String date, Double humidity, Double windSpeed,
                                  Double maxTemp, String alert, Double minTemp,
                                  Double maxWindSpeed) {
        this.date = date;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.maxWindSpeed = maxWindSpeed;
        this.alert = alert;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getAlert() {
        return alert;
    }
    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(Double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }
}

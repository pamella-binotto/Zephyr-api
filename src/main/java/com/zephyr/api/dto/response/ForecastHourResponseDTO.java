package com.zephyr.api.dto.response;

public class ForecastHourResponseDTO {

    private String dateTime;
    private Double temperature;
    private Double humidity;
    private Double windSpeed;
    private Double rainProbability;
    private String windAlert;
    private String rainAlert

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
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

    public Double getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(Double rainProbability) {
        this.rainProbability = rainProbability;
    }

    public String getWindAlert() {
        return windAlert;
    }

    public void setWindAlert(String windAlert) {
        this.windAlert = windAlert;
    }

    public String getRainAlert() {
        return rainAlert;
    }

    public void setRainAlert(String rainAlert) {
        this.rainAlert = rainAlert;
    }

    public ForecastHourResponseDTO(String dateTime, Double temperature,
                                   Double humidity, Double windSpeed,
                                   Double rainProbability, String windAlert, String rainAlert) {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.rainProbability = rainProbability;


    }
}
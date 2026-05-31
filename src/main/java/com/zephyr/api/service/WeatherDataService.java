package com.zephyr.api.service;


import com.zephyr.api.client.WeatherApiClient;
import com.zephyr.api.dto.ForecastItemDTO;
import com.zephyr.api.dto.WeatherDataRequestDTO;
import com.zephyr.api.dto.external.WeatherResponseDTO;
import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.dto.response.ForecastDayResponseDTO;
import com.zephyr.api.dto.response.ForecastHourResponseDTO;
import com.zephyr.api.dto.response.ForecastResponseDTO;
import com.zephyr.api.entity.WeatherData;
import com.zephyr.api.exception.WeatherDataNotFoundException;
import com.zephyr.api.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeatherDataService {

    private final WeatherDataRepository repository;
    private final WeatherApiClient apiClient;


    public WeatherDataService(WeatherDataRepository repository, WeatherApiClient apiClient) {
        this.repository = repository;
        this.apiClient = apiClient;
    }


    public WeatherData save(WeatherData weatherData) {
        return repository.save(weatherData);
    }

    public WeatherData save(WeatherDataRequestDTO dto) {

        WeatherData weatherData = new WeatherData();

        weatherData.setTemperature(dto.getTemperature());
        weatherData.setHumidity(dto.getHumidity());
        weatherData.setCity(dto.getCity());


        return repository.save(weatherData);
    }


    public List<WeatherData> findAll() {
        return repository.findAll();
    }

    public WeatherData findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new WeatherDataNotFoundException("Weather Data Not Found."));
    }

    public void delete(Long id) {
        WeatherData weatherData = repository.findById(id).
                orElseThrow(() -> new WeatherDataNotFoundException("Weather Data Not Found."));
        repository.delete(weatherData);
    }

    public CurrentWeatherResponseDTO getCurrentWeather(String city) {

        WeatherResponseDTO response = apiClient.getWeatherByCity(city);

        Double windSpeedKm = (double) Math.round(response.getWind().getSpeed() * 3.6);

        String alert;

        if (windSpeedKm >= 80) {
            alert = "Alerta severo de ventos fortes";
        } else if (windSpeedKm >= 60) {
            alert = "Evite deslocamentos de moto ou bicicleta.";
        } else if (windSpeedKm >= 40) {
            alert = "Ventos fortes no dia de hoje";
        } else {
            alert = "Condições climáticas estáveis.";
        }


        WeatherData weatherData = new WeatherData();

        weatherData.setTemperature(response.getMain().getTemp());
        weatherData.setHumidity(response.getMain().getHumidity());
        weatherData.setCity(city);


        repository.save(weatherData);


        return new CurrentWeatherResponseDTO(
                city,
                response.getMain().getTemp(),
                response.getMain().getHumidity(),
                windSpeedKm,
                alert

        );

    }

    public List<ForecastDayResponseDTO> getForecast(String city) {

        ForecastResponseDTO response =
                apiClient.getForecastByCity(city);

        Map<String, ForecastDayResponseDTO> forecastMap = new LinkedHashMap<>();


        for (
                ForecastItemDTO item : response.getList()) {
            String date = item.getDt_txt().split(" ")[0];

            Double windSpeedKm =
                    (double) Math.round(item.getWind().getSpeed() * 3.6);

            Double rainProbability =
                    (double) Math.round(item.getPop() * 100);

            String alert;


            if (windSpeedKm >= 80) {
                alert = "Alerta severo de ventos fortes";
            } else if (windSpeedKm >= 60) {
                alert = "Evite deslocamentos de moto ou bicicleta.";
            } else if (windSpeedKm >= 40) {
                alert = "Ventos fortes no dia de hoje";
            } else {
                alert = "Condições climáticas estáveis.";
            }

            if (!forecastMap.containsKey(date)) {

                ForecastDayResponseDTO dto =
                        new ForecastDayResponseDTO(

                                date,
                                item.getMain().getHumidity(),
                                rainProbability,
                                windSpeedKm,
                                windSpeedKm,
                                alert,
                                item.getMain().getTemp(),
                                item.getMain().getTemp()

                        );

                forecastMap.put(date, dto);
            } else {
                ForecastDayResponseDTO existingForecast =
                        forecastMap.get(date);

                existingForecast.setMinTemp(
                        Math.min(
                                existingForecast.getMinTemp(),
                                item.getMain().getTemp()
                        )
                );

                existingForecast.setMaxTemp(
                        Math.max(
                                existingForecast.getMaxTemp(),
                                item.getMain().getTemp()
                        )
                );

                existingForecast.setMaxWindSpeed(
                        Math.max(
                                existingForecast.getMaxWindSpeed(),
                                windSpeedKm
                        )
                );
                existingForecast.setRainProbability(
                        Math.max(
                                existingForecast.getRainProbability(),
                                rainProbability

                        )
                );

            }
        }
        return new ArrayList<>(forecastMap.values());

    }

    public List<ForecastHourResponseDTO> getHourlyForecast(String city){

        ForecastResponseDTO response =
                apiClient.getForecastByCity(city);


        List<ForecastHourResponseDTO> hourlyForecast = new ArrayList<>();

        for (ForecastItemDTO item : response.getList()){

            Double windSpeedKm =
                    (double) Math.round(item.getWind().getSpeed() * 3.6);

            Double rainProbability =
                    (double) Math.round(item.getPop() * 100);

            String windAlert;
            String rainAlert;

            if (windSpeedKm >= 80) {
                windAlert = "Alerta severo de ventos fortes";
            } else if (windSpeedKm >= 60) {
                windAlert = "Evite deslocamentos de moto ou bicicleta.";
            } else if (windSpeedKm >= 40) {
                windAlert = "Ventos fortes no dia de hoje";
            } else {
                windAlert = "Condições climáticas estáveis.";
            }

            if (rainProbability >= 70){
                rainAlert = "Alta probabilidade de chuva";
            } else if (rainProbability >= 50) {
                rainAlert = "Probabilidade moderada de chuvas";
            } else if (rainProbability >= 20){
                rainAlert = "Probabilidade baixa de chuva";
            } else {
                rainAlert = "Não há probabilidade de chuvas";
            }


            ForecastHourResponseDTO dto =
                    new ForecastHourResponseDTO(
                            item.getDt_txt(),
                            item.getMain().getTemp(),
                            item.getMain().getHumidity(),
                            windSpeedKm,
                            rainProbability,
                            windAlert,
                            rainAlert
                    );

            hourlyForecast.add(dto);
        }
        return hourlyForecast;
    }




}

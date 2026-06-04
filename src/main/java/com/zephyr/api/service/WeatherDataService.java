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
import org.springframework.cache.annotation.Cacheable;

import java.util.*;

@Service
public class WeatherDataService {

    private final WeatherDataRepository repository;
    private final WeatherApiClient apiClient;


    private String getWindAlert(Double windSpeedKm) {

        if (windSpeedKm >= 80) {
            return "Alerta severo de ventos fortes";
        } else if (windSpeedKm >= 60) {
            return "Evite deslocamentos de moto ou bicicleta.";
        } else if (windSpeedKm >= 40) {
            return "Ventos fortes no dia de hoje";
        }

        return "Condições climáticas estáveis.";
    }

    private String getRainAlert(Double rainProbability) {

        if (rainProbability >= 70) {
            return "Alta probabilidade de chuva";
        } else if (rainProbability >= 50) {
            return "Probabilidade moderada de chuvas";
        } else if (rainProbability >= 20) {
            return "Probabilidade baixa de chuva";
        }

        return "Não há probabilidade de chuvas";
    }

    private String getDailySummary(Double windSpeedKm, Double rainProbability) {
        if (windSpeedKm >= 60 && rainProbability >= 70) {
            return "Evite deslocamentos e leve proteção contra chuva.";
        }

        if (windSpeedKm >= 60) {
            return "Evite deslocamentos de moto ou bicicleta.";
        }

        if (rainProbability >= 70) {
            return "Leve guarda-chuva ao sair de casa.";
        }

        return "Condições favoráveis para atividades externas.";
    }

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

    @Cacheable("currentWeather")
    public CurrentWeatherResponseDTO getCurrentWeather(String city) {

        System.out.println("Consultando API externa");

        WeatherResponseDTO response = apiClient.getWeatherByCity(city);

        Double windSpeedKm = (double) Math.round(response.getWind().getSpeed() * 3.6);

        String windAlert = getWindAlert(windSpeedKm);



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
                windAlert

        );

    }

    @Cacheable("forecast")
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

            String windAlert = getWindAlert(windSpeedKm);
            String rainAlert = getRainAlert(rainProbability);


            if (!forecastMap.containsKey(date)) {

                ForecastDayResponseDTO dto =
                        new ForecastDayResponseDTO(

                                date,
                                item.getMain().getHumidity(),
                                rainProbability,
                                windSpeedKm,
                                windSpeedKm,
                                windAlert,
                                rainAlert,
                                item.getMain().getTemp(),
                                item.getMain().getTemp(),
                                null

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


        List<ForecastDayResponseDTO> forecastList =
                new ArrayList<>(forecastMap.values());

        for (ForecastDayResponseDTO dto : forecastList) {

            dto.setDailySummary(
                    getDailySummary(
                            dto.getMaxWindSpeed(),
                            dto.getRainProbability()
                    )
            );
        }

        forecastList.sort(
                Comparator.comparing(
                        ForecastDayResponseDTO::getDate
                )
        );

        return forecastList;

    }

    @Cacheable("hourlyForecast")
    public List<ForecastHourResponseDTO> getHourlyForecast(String city){

        ForecastResponseDTO response =
                apiClient.getForecastByCity(city);


        List<ForecastHourResponseDTO> hourlyForecast = new ArrayList<>();

        for (ForecastItemDTO item : response.getList()){

            Double windSpeedKm =
                    (double) Math.round(item.getWind().getSpeed() * 3.6);

            Double rainProbability =
                    (double) Math.round(item.getPop() * 100);

            String windAlert = getWindAlert(windSpeedKm);
            String rainAlert = getRainAlert(rainProbability);



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

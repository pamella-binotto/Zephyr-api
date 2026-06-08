package com.zephyr.api;

import com.zephyr.api.client.WeatherApiClient;
import com.zephyr.api.dto.ForecastItemDTO;
import com.zephyr.api.dto.external.MainWeatherDTO;
import com.zephyr.api.dto.external.WeatherResponseDTO;
import com.zephyr.api.dto.external.WindDTO;
import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.dto.response.ForecastDayResponseDTO;
import com.zephyr.api.dto.response.ForecastResponseDTO;
import com.zephyr.api.exception.WeatherDataNotFoundException;
import com.zephyr.api.repository.WeatherDataRepository;
import com.zephyr.api.service.WeatherDataService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeatherDataServiceTest {

    private WeatherApiClient apiClient;
    private WeatherDataRepository repository;
    private WeatherDataService service;

    private Double kmToMs(Double kmh) {
        return kmh / 3.6;
    }

    @BeforeEach
    public void setup() {

        apiClient = Mockito.mock(WeatherApiClient.class);
        repository = Mockito.mock(WeatherDataRepository.class);
        service = new WeatherDataService(repository, apiClient);
    }

    private WeatherResponseDTO createWeatherResponse(Double windSpeed) {

        MainWeatherDTO main = new MainWeatherDTO();
        main.setTemp(20.0);
        main.setHumidity(80.0);

        WindDTO wind = new WindDTO();
        wind.setSpeed(windSpeed);

        WeatherResponseDTO response = new WeatherResponseDTO();
        response.setMain(main);
        response.setWind(wind);

        return response;
    }

    @Test
    void shouldReturnSevereWindAlert() {


        WeatherResponseDTO response =
                createWeatherResponse(kmToMs(85.0));

        Mockito.when(
                apiClient.getWeatherByCity("Florianopolis")
        ).thenReturn(response);

        CurrentWeatherResponseDTO result =
                service.getCurrentWeather("Florianopolis");


        assertEquals(
                "Alerta severo de ventos fortes",
                result.getAlert()
        );
    }

    @Test
    void shouldReturnStableWeatherAlert() {

        WeatherResponseDTO response =
                createWeatherResponse(kmToMs(10.0));

        Mockito.when(
                apiClient.getWeatherByCity("Florianopolis")
        ).thenReturn(response);

        CurrentWeatherResponseDTO result =
                service.getCurrentWeather("Florianopolis");


        assertEquals(
                "Condições climáticas estáveis.",
                result.getAlert()
        );
    }

    @Test
    void shouldReturnStrongWeatherAlert() {

        WeatherResponseDTO response =
                createWeatherResponse(kmToMs(65.0));

        Mockito.when(
                apiClient.getWeatherByCity("Florianopolis")
        ).thenReturn(response);

        CurrentWeatherResponseDTO result =
                service.getCurrentWeather("Florianopolis");


        assertEquals(
                "Evite deslocamentos de moto ou bicicleta.",
                result.getAlert()
        );
    }

    @Test
    void shouldReturnModerateWeatherAlert() {

        WeatherResponseDTO response =
                createWeatherResponse(kmToMs(45.0));

        Mockito.when(
                apiClient.getWeatherByCity("Florianopolis")
        ).thenReturn(response);

        CurrentWeatherResponseDTO result =
                service.getCurrentWeather("Florianopolis");


        assertEquals(
                "Ventos fortes no dia de hoje",
                result.getAlert()
        );
    }


    @Test
    void shouldReturnCombinadDailySummary() {

        MainWeatherDTO main = new MainWeatherDTO();
        main.setTemp(25.0);
        main.setHumidity(80.0);

        WindDTO wind = new WindDTO();
        wind.setSpeed(kmToMs(80.0));

        ForecastItemDTO item =
                new ForecastItemDTO(
                        main,
                        wind,
                        "2026-06-10 12:00:00",
                        0.8
                );

        List<ForecastItemDTO> items =
                List.of(item);

        ForecastResponseDTO response =
                new ForecastResponseDTO(items);

        Mockito.when(
                apiClient.getForecastByCity("Florianopolis")
        ).thenReturn(response);

        List<ForecastDayResponseDTO> result =
                service.getForecast("Florianopolis");

        ForecastDayResponseDTO forecast =
                result.get(0);

        assertEquals(
                "Evite deslocamentos e leve proteção contra chuva.",
                forecast.getDailySummary()
        );

    }


    @Test
    void shouldReturnJustRainDailySummary() {

        MainWeatherDTO main = new MainWeatherDTO();
        main.setTemp(25.0);
        main.setHumidity(80.0);

        WindDTO wind = new WindDTO();
        wind.setSpeed(kmToMs(1.0));

        ForecastItemDTO item =
                new ForecastItemDTO(
                        main,
                        wind,
                        "2026-06-10 12:00:00",
                        0.7
                );

        List<ForecastItemDTO> items =
                List.of(item);

        ForecastResponseDTO response =
                new ForecastResponseDTO(items);

        Mockito.when(
                apiClient.getForecastByCity("Florianopolis")
        ).thenReturn(response);

        List<ForecastDayResponseDTO> result =
                service.getForecast("Florianopolis");

        ForecastDayResponseDTO forecast =
                result.get(0);

        assertEquals(
                "Leve guarda-chuva ao sair de casa.",
                forecast.getDailySummary()
        );

    }
    @Test
    void shouldReturnJustWindDailySummary() {

        MainWeatherDTO main = new MainWeatherDTO();
        main.setTemp(25.0);
        main.setHumidity(80.0);

        WindDTO wind = new WindDTO();
        wind.setSpeed(kmToMs(65.0));

        ForecastItemDTO item =
                new ForecastItemDTO(
                        main,
                        wind,
                        "2026-06-10 12:00:00",
                        0.1
                );

        List<ForecastItemDTO> items =
                List.of(item);

        ForecastResponseDTO response =
                new ForecastResponseDTO(items);

        Mockito.when(
                apiClient.getForecastByCity("Florianopolis")
        ).thenReturn(response);

        List<ForecastDayResponseDTO> result =
                service.getForecast("Florianopolis");

        ForecastDayResponseDTO forecast =
                result.get(0);

        assertEquals(
                "Evite deslocamentos de moto ou bicicleta.",
                forecast.getDailySummary()
        );

    }

    @Test
    void shouldThrowExceptionWhenWeatherDataNotFound() {

        Mockito.when(
                repository.findById(1L)
        ).thenReturn(Optional.empty());

        WeatherDataNotFoundException exception =
                assertThrows(
                        WeatherDataNotFoundException.class,
                        () -> service.findById(1L)
                );

        assertEquals(
                "Weather Data Not Found.",
                exception.getMessage()
        );
    }


}

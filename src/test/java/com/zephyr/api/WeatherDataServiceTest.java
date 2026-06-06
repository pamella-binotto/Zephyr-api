package com.zephyr.api;

import com.zephyr.api.client.WeatherApiClient;
import com.zephyr.api.dto.external.MainWeatherDTO;
import com.zephyr.api.dto.external.WeatherResponseDTO;
import com.zephyr.api.dto.external.WindDTO;
import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.repository.WeatherDataRepository;
import com.zephyr.api.service.WeatherDataService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}

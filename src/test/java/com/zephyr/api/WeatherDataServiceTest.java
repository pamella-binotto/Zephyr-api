package com.zephyr.api;

import com.zephyr.api.client.WeatherApiClient;
import com.zephyr.api.dto.external.MainWeatherDTO;
import com.zephyr.api.dto.external.WeatherResponseDTO;
import com.zephyr.api.dto.external.WindDTO;
import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.repository.WeatherDataRepository;
import com.zephyr.api.service.WeatherDataService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherDataServiceTest {

    @Test
    void shouldReturnSevereWindAlert(){

        WeatherApiClient apiClient =
                Mockito.mock(WeatherApiClient.class);

        WeatherDataRepository repository =
                Mockito.mock(WeatherDataRepository.class);

        WeatherDataService service = new WeatherDataService(repository, apiClient);

        MainWeatherDTO main = new MainWeatherDTO();

        main.setTemp(20.0);
        main.setHumidity(80.0);

        WindDTO wind = new WindDTO();

        wind.setSpeed(23.6);

        WeatherResponseDTO response =
                new WeatherResponseDTO();

        response.setMain(main);
        response.setWind(wind);

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
}

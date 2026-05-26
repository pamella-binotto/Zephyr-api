package com.zephyr.api.controller;


import com.zephyr.api.dto.WeatherDataRequestDTO;
import com.zephyr.api.dto.external.WeatherResponseDTO;
import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.entity.WeatherData;
import com.zephyr.api.service.WeatherDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherDataController {

    private final WeatherDataService service;

    public WeatherDataController(WeatherDataService service) {
        this.service = service;
    }

    @Operation(summary = "Create weather data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Weather data created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @PostMapping
    public ResponseEntity<WeatherData> createWeather(@Valid @RequestBody WeatherDataRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }


    @Operation(summary = "Get all weather data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather data found with successfully"),
            @ApiResponse(responseCode = "404", description = "Weather data not found")
    })
    @GetMapping()
    public ResponseEntity<List<WeatherData>> findAllWeatherData() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @Operation(summary = "Get weather data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather data retrieved successfully")
    })
    @GetMapping("{id}")
    public ResponseEntity<WeatherData> findWeatherDataById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }


    @Operation(summary = "Get weather data by city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "City retrieved successfully")
    })
    @GetMapping("/current/{city}")
    public ResponseEntity<CurrentWeatherResponseDTO> getCurrentWeatherByCity(@PathVariable String city) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getCurrentWeather(city));
    }


    @Operation(summary = "Delete weather data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Weather data deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Weather data not found")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWeather(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }


    @Operation(summary = "Update weather data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather data updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Weather data not found")
    }
    )
    @PutMapping("{id}")
    public ResponseEntity<WeatherData> updateWeatherData(@PathVariable Long id,
                                                         @Valid @RequestBody WeatherDataRequestDTO dto) {

        WeatherData existing = service.findById(id);

        existing.setTemperature(dto.getTemperature());
        existing.setHumidity(dto.getHumidity());

        return ResponseEntity.status(HttpStatus.OK).body(service.save(existing));
    }
}

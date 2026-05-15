package com.zephyr.api.controller;


import com.zephyr.api.dto.WeatherDataRequestDTO;
import com.zephyr.api.entity.WeatherData;
import com.zephyr.api.service.WeatherDataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherDataController
{

    private final WeatherDataService service;
    public WeatherDataController(WeatherDataService service)
    {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<WeatherData> createWeather(@Valid @RequestBody WeatherDataRequestDTO dto){
     return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
 }

    @GetMapping()
    public ResponseEntity <List<WeatherData>> findAllWeatherData(){
        return  ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }


    @GetMapping("{id}")
    public ResponseEntity <WeatherData> findWeatherDataById(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity <Void> deleteWeather(@PathVariable Long id){

        service.delete(id);

        return ResponseEntity.noContent().build();

    }


    @PutMapping("{id}")
    public ResponseEntity<WeatherData> updateWeatherData(@PathVariable Long id,
                                        @Valid @RequestBody WeatherDataRequestDTO dto) {

        WeatherData existing = service.findById(id);

        existing.setTemperature(dto.getTemperature());
        existing.setHumidity(dto.getHumidity());

        return ResponseEntity.status(HttpStatus.OK).body(service.save(existing));
    }
}

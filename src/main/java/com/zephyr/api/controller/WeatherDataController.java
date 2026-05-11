package com.zephyr.api.controller;


import com.zephyr.api.dto.WeatherDataRequestDTO;
import com.zephyr.api.entity.WeatherData;
import com.zephyr.api.service.WeatherDataService;
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
    public WeatherData createWeather(@RequestBody WeatherDataRequestDTO dto){
     return service.save(dto);
 }

    @GetMapping()
    public List<WeatherData> findAllWeatherData(){
        return service.findAll();
    }


    @GetMapping("{id}")
    public WeatherData findWeatherDataById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteWeather(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("{id}")
    public WeatherData updateWeatherData(@PathVariable Long id,
                                         @RequestBody WeatherDataRequestDTO dto) {

        WeatherData existing = service.findById(id);

        existing.setTemperature(dto.getTemperature());
        existing.setHumidity(dto.getHumidity());

        return service.save(existing);
    }
}

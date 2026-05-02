package com.zephyr.api.controller;


import com.zephyr.api.WeatherData;
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
    public WeatherData createWeather(@RequestBody WeatherData weatherData){
     return service.save(weatherData);
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

    @PutMapping("/{id}")
    public WeatherData updateWeatherData(@PathVariable Long id,
                                         @RequestBody WeatherData weatherData) {

        WeatherData existing = service.findById(id);

        existing.setTemperature(weatherData.getTemperature());
        existing.setHumidity(weatherData.getHumidity());

        return service.save(existing);
    }
}

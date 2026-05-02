package com.zephyr.api.controller;


import com.zephyr.api.WeatherData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherDataController
{

 @PostMapping
    public WeatherData createWeather(@RequestBody WeatherData weatherData){
     return weatherData;
 }


}

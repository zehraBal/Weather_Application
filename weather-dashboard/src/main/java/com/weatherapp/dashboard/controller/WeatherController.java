package com.weatherapp.dashboard.controller;

import com.weatherapp.dashboard.dto.WeatherResponse;
import com.weatherapp.dashboard.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService=weatherService;
    }

    @GetMapping
    public WeatherResponse getWeather(@RequestParam String city){
return null;    }
}

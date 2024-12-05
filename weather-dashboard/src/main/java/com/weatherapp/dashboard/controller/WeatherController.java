package com.weatherapp.dashboard.controller;

import com.weatherapp.dashboard.converter.CurrentWeatherConverter;
import com.weatherapp.dashboard.dto.CurrentWeatherResponse;
import com.weatherapp.dashboard.entity.CurrentWeather;
import com.weatherapp.dashboard.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/current-weather")
@AllArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final CurrentWeatherConverter converter;


    @GetMapping
    public CurrentWeatherResponse fetchAndSaveCurrentWeather(@RequestParam String city) {
        CurrentWeather currentWeather = weatherService.fetchAndSaveCurrentWeather(city);
        return converter.toDto(currentWeather);
    }
}

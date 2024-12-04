package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.entity.CurrentWeather;

public interface WeatherService {
    CurrentWeather fetchAndSaveCurrentWeather(String city);
}

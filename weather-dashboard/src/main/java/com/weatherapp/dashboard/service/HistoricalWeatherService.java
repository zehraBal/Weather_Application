package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.entity.HistoricalWeather;

import java.time.LocalDate;
import java.util.List;

public interface HistoricalWeatherService {
    HistoricalWeather saveHistoricalWeather(String city, LocalDate date, double temperature, int humidity, String description, double windSpeed);
    public List<HistoricalWeather> getHistoricalWeather(String city);
    public List<HistoricalWeather> getHistoricalWeather(String city, LocalDate startDate, LocalDate endDate);

}

package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.dto.HistoricalWeatherRequest;
import com.weatherapp.dashboard.entity.HistoricalWeather;

import java.time.LocalDate;
import java.util.List;

public interface HistoricalWeatherService {
    HistoricalWeather saveHistoricalWeather(HistoricalWeather historicalWeather);
    public List<HistoricalWeather> getHistoricalWeather(String city);
    public List<HistoricalWeather> getHistoricalWeather(String city, LocalDate startDate, LocalDate endDate);
    public List<HistoricalWeather> getAllHistoricalWeather();
}

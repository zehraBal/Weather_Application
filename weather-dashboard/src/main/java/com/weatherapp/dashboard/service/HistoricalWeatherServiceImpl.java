package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.entity.HistoricalWeather;
import com.weatherapp.dashboard.repository.HistoricalWeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
public class HistoricalWeatherServiceImpl implements HistoricalWeatherService{

    private final HistoricalWeatherRepository historicalWeatherRepository;

    @Override
    public HistoricalWeather saveHistoricalWeather(HistoricalWeather historicalWeather) {
        return historicalWeatherRepository.save(historicalWeather);
    }

    @Override
    public List<HistoricalWeather> getHistoricalWeather(String city) {
        return historicalWeatherRepository.findByLocationCity(city);
    }

    @Override
    public List<HistoricalWeather> getHistoricalWeather(String city, LocalDate startDate, LocalDate endDate) {
        return historicalWeatherRepository.findByLocationCityAndDateBetween(city,startDate,endDate);
    }

    @Override
    public List<HistoricalWeather> getAllHistoricalWeather() {
        return historicalWeatherRepository.findAll();
    }
}

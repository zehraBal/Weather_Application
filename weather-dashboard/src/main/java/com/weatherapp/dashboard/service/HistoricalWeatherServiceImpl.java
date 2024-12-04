package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.entity.HistoricalWeather;
import com.weatherapp.dashboard.entity.Location;
import com.weatherapp.dashboard.repository.HistoricalWeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
public class HistoricalWeatherServiceImpl implements HistoricalWeatherService{

    private final HistoricalWeatherRepository historicalWeatherRepository;
    private final LocationService locationService;

    @Override
    public HistoricalWeather saveHistoricalWeather(String city, LocalDate date, double temperature, int humidity, String description, double windSpeed) {
        Location location=locationService.findByCity(city).orElseThrow(()->new RuntimeException("Location not found !"));

        HistoricalWeather historicalWeather =new HistoricalWeather(location,date,temperature,humidity,description,windSpeed);
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
}

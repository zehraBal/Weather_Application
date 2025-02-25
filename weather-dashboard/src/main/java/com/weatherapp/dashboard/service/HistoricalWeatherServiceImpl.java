package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.entity.HistoricalWeather;
import com.weatherapp.dashboard.entity.User;
import com.weatherapp.dashboard.repository.HistoricalWeatherRepository;
import com.weatherapp.dashboard.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoricalWeatherServiceImpl {

    private final HistoricalWeatherRepository historicalWeatherRepository;
    private final UserRepository userRepository;

    public List<HistoricalWeather> getUserHistoricalWeather(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return historicalWeatherRepository.findByUser(user);
    }

    public List<HistoricalWeather> getUserHistoricalWeather(String username, String city, LocalDate startDate, LocalDate endDate) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return historicalWeatherRepository.findByUserAndCityAndDateBetween(user, city, startDate, endDate);
    }

    public HistoricalWeather saveHistoricalWeather(HistoricalWeather historicalWeather, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        historicalWeather.setUser(user);
        return historicalWeatherRepository.save(historicalWeather);
    }
}

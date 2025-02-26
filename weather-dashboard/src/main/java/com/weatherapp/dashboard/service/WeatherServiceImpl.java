package com.weatherapp.dashboard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherapp.dashboard.dto.WeatherResponse;
import com.weatherapp.dashboard.entity.CurrentWeather;
import com.weatherapp.dashboard.entity.HistoricalWeather;
import com.weatherapp.dashboard.entity.Location;
import com.weatherapp.dashboard.entity.User;
import com.weatherapp.dashboard.repository.CurrentWeatherRepository;
import com.weatherapp.dashboard.repository.HistoricalWeatherRepository;
import com.weatherapp.dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${spring.weather.api-key}")
    private String apiKey;

    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final LocationService locationService;
    private final HistoricalWeatherRepository historicalWeatherRepository;

    @Override
    public CurrentWeather fetchAndSaveCurrentWeather(String city) {
        return fetchAndSaveCurrentWeather(city, null);
    }

    @Override
    public CurrentWeather fetchAndSaveCurrentWeather(String city, String token) {
        try {
            String url = String.format("%s?q=%s&APPID=%s&units=metric", API_URL, city, apiKey);
            String jsonResponse = restTemplate.getForObject(url, String.class);
            WeatherResponse response = objectMapper.readValue(jsonResponse, WeatherResponse.class);

            Location location = locationService.saveOrUpdateLocation(
                    new Location(response.name(), response.sys().country(), response.coord().lat(), response.coord().lon())
            );

            User user = getAuthenticatedUser();
            if (user != null) {
                saveHistoricalWeather(response, location, user);
            }

            return createCurrentWeather(response, location);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch or save current weather data", e);
        }
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            return userRepository.findByUsername(authentication.getName()).orElse(null);
        }
        return null;
    }

    private void saveHistoricalWeather(WeatherResponse response, Location location, User user) {
        HistoricalWeather weather = new HistoricalWeather();
        weather.setLocation(location);
        weather.setTemperature(response.main().temp());
        weather.setHumidity(response.main().humidity());
        weather.setDescription(response.weather().get(0).description());
        weather.setWindSpeed(response.wind().speed());
        weather.setDate(LocalDate.now());
        weather.setUser(user);
        historicalWeatherRepository.save(weather);
    }

    private CurrentWeather createCurrentWeather(WeatherResponse response, Location location) {
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setLocation(location);
        currentWeather.setTemperature(response.main().temp());
        currentWeather.setHumidity(response.main().humidity());
        currentWeather.setDescription(response.weather().get(0).description());
        currentWeather.setWindSpeed(response.wind().speed());
        currentWeather.setTimestamp(LocalDateTime.now());
        return currentWeather;
    }
}

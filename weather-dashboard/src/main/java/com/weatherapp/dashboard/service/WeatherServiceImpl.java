package com.weatherapp.dashboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherapp.dashboard.dto.Weather;
import com.weatherapp.dashboard.dto.WeatherResponse;
import com.weatherapp.dashboard.entity.CurrentWeather;
import com.weatherapp.dashboard.entity.HistoricalWeather;
import com.weatherapp.dashboard.entity.Location;
import com.weatherapp.dashboard.repository.CurrentWeatherRepository;
import com.weatherapp.dashboard.repository.HistoricalWeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final LocationService locationService;
    private final HistoricalWeatherRepository historicalWeatherRepository;
    private final CurrentWeatherRepository currentWeatherRepository;
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "8b86cfa5c05559d88e79b598ec36acbf";

    @Override
    public CurrentWeather fetchAndSaveCurrentWeather(String city) {
        try{
            String url = API_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
            String jsonResponse=restTemplate.getForObject(url, String.class);
            WeatherResponse response = objectMapper.readValue(jsonResponse, WeatherResponse.class);
            Location location = locationService.saveOrUpdateLocation(
                    new Location(response.name(), response.sys().country(), response.coord().lat(), response.coord().lon())
            );

            HistoricalWeather weather=new HistoricalWeather();
            weather.setLocation(location);
            weather.setTemperature(response.main().temp());
            weather.setHumidity(response.main().humidity());
            weather.setDescription(response.weather().get(0).description());
            weather.setWindSpeed(response.wind().speed());
            weather.setDate(LocalDate.now());
            historicalWeatherRepository.save(weather);

            CurrentWeather currentWeather=new CurrentWeather();
            currentWeather.setLocation(location);
            currentWeather.setTemperature(response.main().temp());
            currentWeather.setHumidity(response.main().humidity());
            currentWeather.setDescription(response.weather().get(0).description());
            currentWeather.setWindSpeed(response.wind().speed());
            currentWeather.setTimestamp(LocalDateTime.now());
            return currentWeather;

        }catch (Exception e) {
            throw new RuntimeException("Failed to fetch or save current weather data", e);
        }
    }

}

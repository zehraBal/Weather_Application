package com.weatherapp.dashboard.converter;

import com.weatherapp.dashboard.dto.CurrentWeatherRequest;
import com.weatherapp.dashboard.dto.CurrentWeatherResponse;
import com.weatherapp.dashboard.entity.CurrentWeather;
import com.weatherapp.dashboard.entity.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrentWeatherConverter {

    public CurrentWeatherResponse toDto(CurrentWeather currentWeather) {
        return new CurrentWeatherResponse(
                currentWeather.getId(),
                currentWeather.getLocation().getCity(),
                currentWeather.getTemperature(),
                currentWeather.getHumidity(),
                currentWeather.getDescription(),
                currentWeather.getWindSpeed(),
                currentWeather.getTimestamp()
        );
    }

    public CurrentWeather toEntity(CurrentWeatherRequest request, Location location) {
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setLocation(location);
        currentWeather.setTemperature(request.temperature());
        currentWeather.setHumidity(request.humidity());
        currentWeather.setDescription(request.description());
        currentWeather.setWindSpeed(request.windSpeed());
        currentWeather.setTimestamp(java.time.LocalDateTime.now());
        return currentWeather;
    }

    public List<CurrentWeatherResponse> toListedDto(List<CurrentWeather> weathers){
        List<CurrentWeatherResponse> responses=new ArrayList<>();

        for(CurrentWeather w : weathers){
            responses.add(toDto(w));
        }
        return responses;
    }
}

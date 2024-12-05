package com.weatherapp.dashboard.converter;

import com.weatherapp.dashboard.dto.CurrentWeatherResponse;
import com.weatherapp.dashboard.dto.HistoricalWeatherRequest;
import com.weatherapp.dashboard.dto.HistoricalWeatherResponse;
import com.weatherapp.dashboard.entity.CurrentWeather;
import com.weatherapp.dashboard.entity.HistoricalWeather;
import com.weatherapp.dashboard.entity.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HistoricalWeatherConverter {

    // Convert HistoricalWeather entity to HistoricalWeatherResponse DTO
    public HistoricalWeatherResponse toDto(HistoricalWeather historicalWeather) {
        return new HistoricalWeatherResponse(
                historicalWeather.getId(),
                historicalWeather.getLocation().getCity(),
                historicalWeather.getDate(),
                historicalWeather.getTemperature(),
                historicalWeather.getHumidity(),
                historicalWeather.getDescription(),
                historicalWeather.getWindSpeed()
        );
    }

    // Convert HistoricalWeatherRequest DTO to HistoricalWeather entity
    public HistoricalWeather toEntity(HistoricalWeatherRequest request, Location location) {
        HistoricalWeather historicalWeather = new HistoricalWeather();
        historicalWeather.setLocation(location);
        historicalWeather.setDate(request.date());
        historicalWeather.setTemperature(request.temperature());
        historicalWeather.setHumidity(request.humidity());
        historicalWeather.setDescription(request.description());
        historicalWeather.setWindSpeed(request.windSpeed());
        return historicalWeather;
    }
    public List<HistoricalWeatherResponse> toListedDto(List<HistoricalWeather> weathers){
        List<HistoricalWeatherResponse> responses=new ArrayList<>();

        for(HistoricalWeather w : weathers){
            responses.add(toDto(w));
        }
        return responses;
    }
}

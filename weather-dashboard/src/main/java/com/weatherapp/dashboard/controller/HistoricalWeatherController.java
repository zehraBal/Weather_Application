package com.weatherapp.dashboard.controller;

import com.weatherapp.dashboard.converter.HistoricalWeatherConverter;
import com.weatherapp.dashboard.dto.HistoricalWeatherRequest;
import com.weatherapp.dashboard.dto.HistoricalWeatherResponse;
import com.weatherapp.dashboard.entity.HistoricalWeather;
import com.weatherapp.dashboard.entity.Location;
import com.weatherapp.dashboard.service.HistoricalWeatherService;
import com.weatherapp.dashboard.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historical")
@AllArgsConstructor
public class HistoricalWeatherController {

    private final HistoricalWeatherService historicalWeatherService;
    private final LocationService locationService;
    private final HistoricalWeatherConverter converter;


    @GetMapping
    public List<HistoricalWeatherResponse> getHistoricalWeather(
            @RequestParam String city,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        List<HistoricalWeather> historicalWeather;
        if (startDate != null && endDate != null) {
            historicalWeather = historicalWeatherService.getHistoricalWeather(
                    city, LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else {
            historicalWeather = historicalWeatherService.getHistoricalWeather(city);
        }
        return converter.toListedDto(historicalWeather);
}
        @PostMapping
        public HistoricalWeatherResponse saveHistoricalWeather(@RequestBody @Valid HistoricalWeatherRequest request) {
            Location location = locationService.findByCity(request.city())
                    .orElseThrow(() -> new RuntimeException("Location not found: " + request.city()));

            HistoricalWeather historicalWeather = converter.toEntity(request, location);
            HistoricalWeather saved = historicalWeatherService.saveHistoricalWeather(historicalWeather);
            return converter.toDto(saved);
        }

}

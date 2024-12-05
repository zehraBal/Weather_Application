package com.weatherapp.dashboard.dto;

import java.time.LocalDate;

public record HistoricalWeatherResponse(
        Long id,
        String city,
        LocalDate date,
        Double temperature,
        Integer humidity,
        String description,
        Double windSpeed
) {}

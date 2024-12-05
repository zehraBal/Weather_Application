package com.weatherapp.dashboard.dto;

import java.time.LocalDateTime;

public record CurrentWeatherResponse(
        Long id,
        String city,
        Double temperature,
        Integer humidity,
        String description,
        Double windSpeed,
        LocalDateTime timestamp
) {}

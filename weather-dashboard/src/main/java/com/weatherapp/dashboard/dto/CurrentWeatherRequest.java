package com.weatherapp.dashboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CurrentWeatherRequest(
        @NotBlank String city,
        @NotNull Double temperature,
        @NotNull Integer humidity,
        @NotBlank String description,
        @NotNull Double windSpeed
) {}
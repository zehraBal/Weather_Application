package com.weatherapp.dashboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record HistoricalWeatherRequest(
        @NotBlank String city,
        @NotNull LocalDate date,
        @NotNull Double temperature,
        @NotNull Integer humidity,
        @NotBlank String description,
        @NotNull Double windSpeed
) {}

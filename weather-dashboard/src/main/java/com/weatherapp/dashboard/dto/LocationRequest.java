package com.weatherapp.dashboard.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocationRequest(    @NotBlank String city,
                                  @NotBlank String country,
                                  @NotNull Double latitude,
                                  @NotNull Double longitude) {
}
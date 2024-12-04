package com.weatherapp.dashboard.dto;

public record LocationResponse(    Long id,
                                   String city,
                                   String country,
                                   double latitude,
                                   double longitude) {
}

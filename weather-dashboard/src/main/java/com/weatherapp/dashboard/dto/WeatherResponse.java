package com.weatherapp.dashboard.dto;

import java.util.List;

public record WeatherResponse(Coord coord, List<Weather> weather,Main main,Wind wind,Sys sys, String name) {
}

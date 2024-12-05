package com.weatherapp.dashboard.repository;

import com.weatherapp.dashboard.entity.CurrentWeather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrentWeatherRepository extends JpaRepository<CurrentWeather,Long> {
    Optional<CurrentWeather> findByLocationCity(String city);
    boolean existsByLocationCity(String city);
}

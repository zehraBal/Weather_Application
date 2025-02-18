package com.weatherapp.dashboard.repository;

import com.weatherapp.dashboard.entity.HistoricalWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface HistoricalWeatherRepository extends JpaRepository<HistoricalWeather,Long> {
    @Query("SELECT h FROM HistoricalWeather h WHERE LOWER(h.city)= LOWER(:city)")
    List<HistoricalWeather> findByLocationCityAndDateBetween(String city, LocalDate startDate,LocalDate endDate);

    List<HistoricalWeather> findByLocationCity(String city);
}
/*
Spring Data JPA automatically derives queries from method names if the field names and relationships are defined properly
 @Query("SELECT hw FROM HistoricalWeather hw " +
       "JOIN hw.location loc " +
       "WHERE loc.city = :city AND hw.date BETWEEN :startDate AND :endDate")
List<HistoricalWeather> findHistoricalWeatherByCityAndDateRange(
    @Param("city") String city,
    @Param("startDate") LocalDate startDate,
    @Param("endDate") LocalDate endDate
);

 */
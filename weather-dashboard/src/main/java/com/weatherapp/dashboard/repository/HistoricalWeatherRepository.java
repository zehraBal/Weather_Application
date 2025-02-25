package com.weatherapp.dashboard.repository;

import com.weatherapp.dashboard.entity.HistoricalWeather;
import com.weatherapp.dashboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface HistoricalWeatherRepository extends JpaRepository<HistoricalWeather,Long> {
    @Query("SELECT h FROM HistoricalWeather h JOIN h.location l WHERE h.user = :user AND LOWER(l.city) = LOWER(:city) AND h.date BETWEEN :startDate AND :endDate")
    List<HistoricalWeather> findByUserAndCityAndDateBetween(User user, String city, LocalDate startDate, LocalDate endDate);
    List<HistoricalWeather> findByUser(User user);

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
package com.weatherapp.dashboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Data
@Table(name="historical_weather")
public class HistoricalWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="location_id",nullable = false)
    private Location location;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double temperature;

    @Column(nullable = false)
    private int humidity;

    @Column(length = 255)
    private String description;

    @Column(name = "wind_speed", nullable = false)
    private double windSpeed;

    public HistoricalWeather(Location location, LocalDate date, double temperature, int humidity, String description, double windSpeed) {
        this.location = location;
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
        this.windSpeed = windSpeed;
    }
}

package com.weatherapp.dashboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="current_weather")
@Data
@NoArgsConstructor
public class CurrentWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="location_id",nullable = false)
    private Location location;

    @Column(nullable = false)
    private  double temperature;

    @Column(nullable = false)
    private int humidity;

    @Column(length = 255)
    private String description;

    @Column(name ="wind_speed",nullable = false)
    private double windSpeed;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public CurrentWeather(Location location, double temperature, int humidity, String description, double windSpeed, LocalDateTime timestamp) {
        this.location = location;
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
        this.windSpeed = windSpeed;
        this.timestamp = timestamp;
    }
}

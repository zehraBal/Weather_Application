package com.weatherapp.dashboard.repository;

import com.weatherapp.dashboard.entity.FavoriteLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation,Long> {
    Optional<FavoriteLocation> findByCity(String city);
    void deleteByCity(String city);

}

package com.weatherapp.dashboard.repository;

import com.weatherapp.dashboard.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location,Long> {

    Optional<Location> findByCity(String city);
    List<Location> findByCountry(String country);
    boolean existByCity(String city);
}

package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.entity.Location;
import com.weatherapp.dashboard.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

public interface LocationService {


    public Location saveOrUpdateLocation(Location location);
    public Optional<Location> findByCity(String city);
    public List<Location> findByCountry(String country);
    boolean existsByCity(String city);

}

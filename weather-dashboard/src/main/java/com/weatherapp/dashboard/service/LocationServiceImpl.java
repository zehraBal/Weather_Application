package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.entity.Location;
import com.weatherapp.dashboard.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;

    @Override
    public Location saveOrUpdateLocation(Location location) {
    Optional<Location> existingLocation=locationRepository.findByCity(location.getCity());
    if(existingLocation.isPresent()){
        return existingLocation.get();
    }
    return locationRepository.save(location);
    }

    @Override
    public Optional<Location> findByCity(String city) {
        return locationRepository.findByCity(city);
    }

    @Override
    public List<Location> findByCountry(String country) {
        return locationRepository.findByCountry(country);
    }

    @Override
    public boolean existByCity(String city) {
        return locationRepository.existByCity(city);
    }
}

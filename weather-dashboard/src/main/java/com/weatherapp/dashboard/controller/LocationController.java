package com.weatherapp.dashboard.controller;

import com.weatherapp.dashboard.converter.LocationConverter;
import com.weatherapp.dashboard.dto.LocationRequest;
import com.weatherapp.dashboard.dto.LocationResponse;
import com.weatherapp.dashboard.entity.Location;
import com.weatherapp.dashboard.service.LocationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final LocationConverter locationConverter;

    @PostMapping
    public LocationResponse addLocation(@RequestBody @Valid LocationRequest request){
        Location location = new Location( request.city(), request.country(), request.latitude(), request.longitude());
        Location savedLocation=locationService.saveOrUpdateLocation(location);
        return locationConverter.toDto(savedLocation);
    }
    @GetMapping("/{city}")
    public LocationResponse getLocation(@PathVariable String city){
        Location location =locationService.findByCity(city).orElseThrow(() -> new RuntimeException("Location not found: " + city));
        return locationConverter.toDto(location);

    }
    @GetMapping("/{country}")
    public List<LocationResponse> getLocationByCountry(@PathVariable String country){
        List<Location> locations =locationService.findByCountry(country);
        return locationConverter.toListedDto(locations);

    }

}

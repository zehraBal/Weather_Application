package com.weatherapp.dashboard.converter;

import com.weatherapp.dashboard.dto.LocationResponse;
import com.weatherapp.dashboard.entity.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationConverter {
    public LocationResponse toDto(Location location) {
        return new LocationResponse(
                location.getId(),
                location.getCity(),
                location.getCountry(),
                location.getLatitude(),
                location.getLongitude()
        );
    }
    public Location toEntity(String city, String country, double latitude, double longitude) {
        return new Location(city, country, latitude, longitude);
    }

    public List<LocationResponse> toListedDto(List<Location> locations){
        List<LocationResponse> responses=new ArrayList<>();
        for(Location l : locations){
            responses.add(toDto(l));
        }
        return responses;
    }
}

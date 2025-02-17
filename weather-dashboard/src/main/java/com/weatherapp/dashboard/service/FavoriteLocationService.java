package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.entity.FavoriteLocation;

import java.util.List;
import java.util.Optional;

public interface FavoriteLocationService {
    Optional<FavoriteLocation> findByCity(String city);
    void removeFromFavorites(String city);
    List<FavoriteLocation> getAllFavorites();
    FavoriteLocation saveFavorite(FavoriteLocation location);

}

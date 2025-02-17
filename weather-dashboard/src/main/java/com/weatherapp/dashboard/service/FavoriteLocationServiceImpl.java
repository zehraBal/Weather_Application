package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.entity.FavoriteLocation;
import com.weatherapp.dashboard.repository.FavoriteLocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteLocationServiceImpl implements FavoriteLocationService{

    private FavoriteLocationRepository favoriteLocationRepository;

    public List<FavoriteLocation> getAllFavorites(){
        return favoriteLocationRepository.findAll();

    }

    public FavoriteLocation saveFavorite(FavoriteLocation location){
        Optional<FavoriteLocation> existing=favoriteLocationRepository.findByCity(location.getCity());
        if(existing.isPresent()){
            throw   new RuntimeException("City already in favorites");
        }
        return favoriteLocationRepository.save(location);
    }

    @Override
    public Optional<FavoriteLocation> findByCity(String city) {
    return favoriteLocationRepository.findByCity(city);
    }

    @Override
    public void removeFromFavorites (String city) {
        if (favoriteLocationRepository.findByCity(city).isPresent()){
            favoriteLocationRepository.deleteByCity(city);
        }
    }
}

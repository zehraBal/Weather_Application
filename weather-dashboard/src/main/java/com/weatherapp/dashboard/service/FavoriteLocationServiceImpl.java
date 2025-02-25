package com.weatherapp.dashboard.service;

import com.weatherapp.dashboard.entity.FavoriteLocation;
import com.weatherapp.dashboard.entity.User;
import com.weatherapp.dashboard.repository.FavoriteLocationRepository;
import com.weatherapp.dashboard.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteLocationServiceImpl {

    private final FavoriteLocationRepository favoriteLocationRepository;
    private final UserRepository userRepository;

    public List<FavoriteLocation> getFavoritesByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return favoriteLocationRepository.findByUser(user);
    }

    public FavoriteLocation saveFavorite(FavoriteLocation location, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (favoriteLocationRepository.findByCityAndUser(location.getCity(), user).isPresent()) {
            throw new RuntimeException("City already in favorites");
        }

        location.setUser(user);
        return favoriteLocationRepository.save(location);
    }

    public Optional<FavoriteLocation> findByCityAndUser(String city, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return favoriteLocationRepository.findByCityAndUser(city, user);
    }

    public void removeFromFavorites(String city, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Optional<FavoriteLocation> favorite = favoriteLocationRepository.findByCityAndUser(city, user);
        if (favorite.isPresent()) {
            favoriteLocationRepository.delete(favorite.get());
        } else {
            throw new RuntimeException("City not found in favorites");
        }
    }
}

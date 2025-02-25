package com.weatherapp.dashboard.repository;

import com.weatherapp.dashboard.entity.FavoriteLocation;
import com.weatherapp.dashboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation, Long> {

    List<FavoriteLocation> findByUser(User user);

    Optional<FavoriteLocation> findByCityAndUser(String city, User user);

    void deleteByCityAndUser(String city, User user);
}

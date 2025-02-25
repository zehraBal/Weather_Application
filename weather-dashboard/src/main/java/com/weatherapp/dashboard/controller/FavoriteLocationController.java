package com.weatherapp.dashboard.controller;

import com.weatherapp.dashboard.dto.FavoriteLocationDTO;
import com.weatherapp.dashboard.entity.FavoriteLocation;
import com.weatherapp.dashboard.service.FavoriteLocationServiceImpl;
import com.weatherapp.dashboard.service.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@AllArgsConstructor
public class FavoriteLocationController {

    private final FavoriteLocationServiceImpl service;
    private final JWTService jwtService;

    // Kullanıcının favorilerini getir
    @GetMapping
    public ResponseEntity<List<FavoriteLocation>> getUserFavorites(@RequestHeader("Authorization") String token) {
        String username = jwtService.extractUserName(token.substring(7));
        return ResponseEntity.ok(service.getFavoritesByUser(username));
    }

    // Favori kaydet
    @PostMapping
    public ResponseEntity<String> saveFavorite(@RequestHeader("Authorization") String token, @RequestBody FavoriteLocationDTO location) {
        String username = jwtService.extractUserName(token.substring(7));
        FavoriteLocation favoriteLocation = new FavoriteLocation();
        favoriteLocation.setCity(location.getCity());
        service.saveFavorite(favoriteLocation, username);
        return ResponseEntity.ok(location.getCity() + " saved successfully");
    }

    // Favoriyi sil
    @DeleteMapping("/{city}")
    public ResponseEntity<String> removeFromFavorites(@RequestHeader("Authorization") String token, @PathVariable String city) {
        String username = jwtService.extractUserName(token.substring(7));
        service.removeFromFavorites(city, username);
        return ResponseEntity.ok(city + " removed from favorites");
    }
}

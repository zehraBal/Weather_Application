package com.weatherapp.dashboard.controller;

import com.weatherapp.dashboard.dto.FavoriteLocationDTO;
import com.weatherapp.dashboard.entity.FavoriteLocation;
import com.weatherapp.dashboard.service.FavoriteLocationService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@AllArgsConstructor
public class FavoriteLocationController {
    private final FavoriteLocationService service;

    @GetMapping
    public List<FavoriteLocation> getAllFavorites(){
        return  service.getAllFavorites();
    }
    @PostMapping
    public ResponseEntity<String> saveFavorite(@RequestBody FavoriteLocationDTO location){
        FavoriteLocation favoriteLocation=new FavoriteLocation();
        favoriteLocation.setCity(location.getCity());
        service.saveFavorite(favoriteLocation);
        return ResponseEntity.ok(location.getCity()+" saved successfully");
    }
    @DeleteMapping("/{city}")
    public void removeFromFavorite(@PathVariable String city){
        service.removeFromFavorites(city);
    }
}

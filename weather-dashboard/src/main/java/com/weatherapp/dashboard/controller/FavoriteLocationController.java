package com.weatherapp.dashboard.controller;

import com.weatherapp.dashboard.entity.FavoriteLocation;
import com.weatherapp.dashboard.service.FavoriteLocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class FavoriteLocationController {
    private final FavoriteLocationService service;

    @GetMapping
    public List<FavoriteLocation> getAllFavorites(){
        return  service.getAllFavorites();
    }
    @PostMapping
    public FavoriteLocation saveFavorite(@RequestBody FavoriteLocation location){
        return service.saveFavorite(location);
    }
    @DeleteMapping("/{city}")
    public void removeFromFavorite(@PathVariable String city){
        service.removeFromFavorites(city);
    }
}

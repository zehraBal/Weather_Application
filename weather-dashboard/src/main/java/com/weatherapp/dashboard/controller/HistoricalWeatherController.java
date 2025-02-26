package com.weatherapp.dashboard.controller;

import com.weatherapp.dashboard.converter.HistoricalWeatherConverter;
import com.weatherapp.dashboard.dto.HistoricalWeatherRequest;
import com.weatherapp.dashboard.dto.HistoricalWeatherResponse;
import com.weatherapp.dashboard.entity.HistoricalWeather;
import com.weatherapp.dashboard.entity.Location;
import com.weatherapp.dashboard.service.HistoricalWeatherServiceImpl;
import com.weatherapp.dashboard.service.JWTService;
import com.weatherapp.dashboard.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/historical")
@AllArgsConstructor
public class HistoricalWeatherController {

    private final HistoricalWeatherServiceImpl historicalWeatherService;
    private final LocationService locationService;
    private final HistoricalWeatherConverter converter;
    private final JWTService jwtService;

    // Kullanıcının tüm geçmiş hava durumu kayıtlarını getir
    @GetMapping
    public List<HistoricalWeatherResponse> getUserHistoricalWeather(@RequestHeader("Authorization") String token) {
        String username = jwtService.extractUserName(token.substring(7));
        List<HistoricalWeather> history = historicalWeatherService.getUserHistoricalWeather(username);
        return converter.toListedDto(history);
    }

    // Kullanıcının belirli bir şehir için geçmiş hava durumu kayıtlarını getir
    @GetMapping("/searchFor")
    public List<HistoricalWeatherResponse> getUserHistoricalWeather(
            @RequestHeader("Authorization") String token,
            @RequestParam String city,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        String username = jwtService.extractUserName(token.substring(7));
        List<HistoricalWeather> historicalWeather;

        if (startDate != null && endDate != null) {
            historicalWeather = historicalWeatherService.getUserHistoricalWeather(username, city, LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else {
            historicalWeather = historicalWeatherService.getUserHistoricalWeather(username, city, null, null);
        }

        return converter.toListedDto(historicalWeather);
    }

//    // Kullanıcının yaptığı hava durumu aramasını kaydet
//    @PostMapping
//    public HistoricalWeatherResponse saveUserHistoricalWeather(@RequestHeader("Authorization") String token, @RequestBody @Valid HistoricalWeatherRequest request) {
//        String username = jwtService.extractUserName(token.substring(7));
//        Location location = locationService.findByCity(request.city())
//                .orElseThrow(() -> new RuntimeException("Location not found: " + request.city()));
//
//        HistoricalWeather historicalWeather = converter.toEntity(request, location);
//        HistoricalWeather saved = historicalWeatherService.saveHistoricalWeather(historicalWeather, username);
//        return converter.toDto(saved);
//    }
}

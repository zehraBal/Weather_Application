package com.weatherapp.dashboard.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Component
public class CustomCorsConfiguration implements CorsConfigurationSource {
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest req) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173")); // Frontend URL
        config.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "OPTIONS")); // OPTIONS eklendi
        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept")); // Başlıkları açıkça tanımla
        config.setAllowCredentials(true); // Kimlik bilgilerini (cookies, Authorization header) destekle

        return config;
    }
}

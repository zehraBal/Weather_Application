package com.weatherapp.dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebConfig {

    private final CorsConfigurationSource customCorsConfiguration;

    public WebConfig(CorsConfigurationSource customCorsConfiguration) {
        this.customCorsConfiguration = customCorsConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF'yi kapat
                .cors(cors -> cors.configurationSource(customCorsConfiguration)) // CORS yapılandırmasını ekle
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/favorites/**").permitAll() // Favori endpoint'ini aç
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

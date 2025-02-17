package com.weatherapp.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebConfig  {
    @Autowired
    CustomCorsConfiguration customCorsConfiguration;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http.csrf(customizer -> customizer.disable());
    http.authorizeHttpRequests(request-> request.anyRequest().permitAll());
        CorsConfigurationSource CustomCorsConfiguration;
        http.cors(c -> c.configurationSource(customCorsConfiguration));
        return http.build();

    }

}

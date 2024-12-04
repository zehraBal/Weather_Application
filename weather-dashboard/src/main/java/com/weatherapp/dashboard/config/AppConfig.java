package com.weatherapp.dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
/*
Synchronous client to perform HTTP requests, exposing a simple, template method API over underlying HTTP client libraries such as the JDK HttpURLConnection, Apache HttpComponents, and others.
RestTemplate offers templates for common scenarios by HTTP method, in addition to the generalized exchange and execute methods that support less frequent cases.
RestTemplate is typically used as a shared component. However, its configuration does not support concurrent modification, and as such its configuration is typically prepared on startup.
If necessary, you can create multiple, differently configured RestTemplate instances on startup. Such instances may use the same underlying ClientHttpRequestFactory if they need to share HTTP client resources.*/
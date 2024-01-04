package com.rahimov.lawfirmmanagementdeploy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow all origins, you may want to restrict this to a specific domain in production
        config.addAllowedOrigin("*");

        // Allow specific HTTP methods (GET, POST, PUT, DELETE, etc.)
        config.addAllowedMethod("*");

        // Allow specific headers
        config.addAllowedHeader("*");

        // Expose any headers that the frontend might need
        config.addExposedHeader("Authorization");

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}

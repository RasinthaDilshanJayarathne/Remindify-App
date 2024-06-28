/**
 * Author : rasintha_j
 * Date : 6/26/2024
 * Time : 2:46 PM
 * Project Name : remindifyapp
 */

package com.remindifyapp.config;

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
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // Allow only specific origins
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        // config.addAllowedOriginPattern("*"); // Use this if you want to allow all origins, but it's not recommended for production
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

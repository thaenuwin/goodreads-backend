
package com.goodreadsbackend.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${access.control.allowed.origin}")
    private String allowedOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        List<String> allowedOrigins = Arrays.stream(allowedOrigin.split(",")).collect(Collectors.toList());
        String allowedOriginAddress = allowedOrigins.get(0);
        String allowedLocalAddress = "";

        if (allowedOrigins.size() == 2) {
            allowedLocalAddress = allowedOrigins.get(1);
        }

        registry.addMapping("/**")
                .allowedOrigins(allowedOriginAddress, allowedLocalAddress)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowCredentials(true);
    }
}

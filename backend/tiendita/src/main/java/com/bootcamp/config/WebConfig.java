package com.bootcamp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // Para Spring Boot < 3.0
        configurer.setUseTrailingSlashMatch(true);

        // Si usas Spring Boot 3+: usa esto en lugar de la línea anterior
        // configurer.setPatternParser(new PathPatternParser());
    }
}

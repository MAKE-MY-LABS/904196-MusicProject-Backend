package com.project.spotifyapp.config;

/*
 * create a configuration class called AppConfig
 *  create a bean called restTemplate
 *  use the RestTemplate class
 *  return the bean
 *  
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}


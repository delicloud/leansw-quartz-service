package com.thoughtworks.lean.quarz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yongliuli on 7/27/16.
 */
@Configuration
public class ApiConfig {
    @Bean(name = "apiRestTemplate")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

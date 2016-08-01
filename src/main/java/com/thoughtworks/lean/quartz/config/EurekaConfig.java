package com.thoughtworks.lean.quartz.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@ConditionalOnProperty(name = "eureka.enabled")
public class EurekaConfig {
    @Override
    public String toString() {
        return "EurekaConfig{}";
    }

}

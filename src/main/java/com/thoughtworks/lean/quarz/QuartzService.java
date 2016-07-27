package com.thoughtworks.lean.quarz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yongliuli on 7/27/16.
 */

@SpringBootApplication
@Configuration
@ComponentScan
public class QuartzService {
    public static void main(String[] args) {
        SpringApplication.run(QuartzService.class, args);
    }
}

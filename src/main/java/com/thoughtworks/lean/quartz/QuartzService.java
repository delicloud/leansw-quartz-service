package com.thoughtworks.lean.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by yongliuli on 7/27/16.
 */

@SpringBootApplication
@EnableAutoConfiguration
public class QuartzService {
    public static void main(String[] args) {
        SpringApplication.run(QuartzService.class, args);
    }
}

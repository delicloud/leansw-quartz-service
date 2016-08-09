package com.thoughtworks.lean.quartz.config;

import com.thoughtworks.lean.quartz.service.JobService;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by yongliuli on 7/27/16.
 */
@Component
public class JobInitConfig {

    @Autowired
    public JobInitConfig(JobService jobService) {
        if (jobService.allJobs().isEmpty()) {
            jobService.init();
        }
    }

}

package com.thoughtworks.lean.quartz.config;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by yongliuli on 7/27/16.
 */
@Configuration
public class QuartzConfig {
    @Autowired
    private Environment environment;

    @Bean(name = "cronTaskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();

        scheduler.setOverwriteExistingJobs(true);
        scheduler.setJobFactory(jobFactory);
        scheduler.setQuartzProperties(quartzProperties());
        return scheduler;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        ClassPathResource[] resources = getQuartzPropertiesByActiveProfile();
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocations(resources);
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }


    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    private ClassPathResource[] getQuartzPropertiesByActiveProfile() {
        List<ClassPathResource> resources = new ArrayList<>();

        List<ClassPathResource> profileResources = Arrays.asList(environment.getActiveProfiles()).stream()
                .map(profile -> new ClassPathResource(String.format("/quartz-%s.properties", profile)))
                .filter(ClassPathResource::exists)
                .collect(Collectors.toList());

        resources.add(new ClassPathResource("/quartz.properties"));
        resources.addAll(profileResources);


        return resources.toArray(new ClassPathResource[resources.size()]);
    }
}

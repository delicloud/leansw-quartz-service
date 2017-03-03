package com.thoughtworks.lean.quartz.config;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

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

        Properties properties = new Properties();
        properties.put("org.quartz.jobStore.class", environment.getProperty("quartz.jobStore.class"));
        properties.put("org.quartz.jobStore.mongoUri", environment.getProperty("quartz.jobStore.mongoUri"));
        properties.put("org.quartz.jobStore.dbName", environment.getProperty("quartz.jobStore.dbName"));
        properties.put("org.quartz.jobStore.collectionPrefix", environment.getProperty("quartz.jobStore.collectionPrefix"));
        properties.put("org.quartz.threadPool.threadCount", environment.getProperty("quartz.threadPool.threadCount"));
        return properties;
    }


    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }


}

package com.thoughtworks.lean.quartz.domain;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.springframework.beans.BeanUtils;

/**
 * Created by yongliuli on 7/27/16.
 */
public class JobDetailDto {

    private static final long serialVersionUID = -6069784757781506897L;
    private String name;
    private String group;
    private String description;
    private Class<? extends Job> jobClass;
    private JobDataMap jobDataMap;
    private boolean durability;
    private boolean shouldRecover;
    private transient JobKey key;
    private String cron;
    private String type;

    public String getCron() {
        return cron;
    }

    public JobDetailDto setCron(String cron) {
        this.cron = cron;
        return this;
    }

    public JobDetailDto() {
    }

    public JobDetailDto(JobDetail jobDetail) {
        BeanUtils.copyProperties(jobDetail, this);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public JobDetailDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public JobDetailDto setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobDetailDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Class<? extends Job> getJobClass() {
        return jobClass;
    }

    public JobDetailDto setJobClass(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
        return this;
    }

    public JobDataMap getJobDataMap() {
        return jobDataMap;
    }

    public JobDetailDto setJobDataMap(JobDataMap jobDataMap) {
        this.jobDataMap = jobDataMap;
        return this;
    }

    public boolean isDurability() {
        return durability;
    }

    public JobDetailDto setDurability(boolean durability) {
        this.durability = durability;
        return this;
    }

    public boolean isShouldRecover() {
        return shouldRecover;
    }

    public JobDetailDto setShouldRecover(boolean shouldRecover) {
        this.shouldRecover = shouldRecover;
        return this;
    }

    public JobKey getKey() {
        return key;
    }

    public JobDetailDto setKey(JobKey key) {
        this.key = key;
        return this;
    }

    public String getType() {
        return type;
    }

    public JobDetailDto setType(String type) {
        this.type = type;
        return this;
    }
}

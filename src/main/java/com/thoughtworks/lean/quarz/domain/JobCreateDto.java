package com.thoughtworks.lean.quarz.domain;

/**
 * Created by yongliuli on 7/27/16.
 */
public class JobCreateDto {
    private String name;
    private String group;
    private String cronExpression;

    public String getName() {
        return name;
    }

    public JobCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public JobCreateDto setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public JobCreateDto setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }
}

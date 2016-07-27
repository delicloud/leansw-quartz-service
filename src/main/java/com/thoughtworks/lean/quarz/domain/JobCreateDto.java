package com.thoughtworks.lean.quarz.domain;

import java.util.Map;

/**
 * Created by yongliuli on 7/27/16.
 */
public class JobCreateDto {
    private String name;
    private String group;
    private String cron;
    private String type;
    private Map<String,Object> dataMap;

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

    public String getCron() {
        return cron;
    }

    public JobCreateDto setCron(String cron) {
        this.cron = cron;
        return this;
    }

    public String getType() {
        return type;
    }

    public JobCreateDto setType(String type) {
        this.type = type;
        return this;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public JobCreateDto setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
        return this;
    }
}

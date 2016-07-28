package com.thoughtworks.lean.quarz.domain;

/**
 * Created by yongliuli on 7/27/16.
 */
public class JobGetTto {
    private String name;
    private String group;

    public String getName() {
        return name;
    }

    public JobGetTto setName(String name) {
        this.name = name;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public JobGetTto setGroup(String group) {
        this.group = group;
        return this;
    }
}

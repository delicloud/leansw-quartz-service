package com.thoughtworks.lean.quarz.domain;

/**
 * Created by yongliuli on 7/27/16.
 */
public class JobDeleteTto {
    private String name;
    private String group;

    public String getName() {
        return name;
    }

    public JobDeleteTto setName(String name) {
        this.name = name;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public JobDeleteTto setGroup(String group) {
        this.group = group;
        return this;
    }
}

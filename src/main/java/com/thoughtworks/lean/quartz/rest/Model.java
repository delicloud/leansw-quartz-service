    package com.thoughtworks.lean.quartz.rest;

import org.hibernate.validator.constraints.NotBlank;

public class Model {
    @NotBlank(message = "test should not empty")
    String test;

    public String getTest() {
        return test;
    }

    public Model setTest(String test) {
        this.test = test;
        return this;
    }
}

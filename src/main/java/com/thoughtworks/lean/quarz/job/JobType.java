package com.thoughtworks.lean.quarz.job;

import com.thoughtworks.lean.quarz.domain.JobCreateDto;

/**
 * Created by yongliuli on 7/27/16.
 */
public enum JobType {
    LOG("LOG", LogJob.class),
    API("API", APIJob.class);
    private Class jobClass;
    private String value;

    JobType(String value, Class jobClass) {
        this.jobClass = jobClass;
        this.value = value;
    }


    @SuppressWarnings("unchecked")
    public static <T extends AbstractJob> Class<T> getJobClass(JobCreateDto jobCreateDto) {
        return valueOf(jobCreateDto.getType()).getJobClass();
    }

    public Class getJobClass() {
        return jobClass;
    }

    public JobType setJobClass(Class jobClass) {
        this.jobClass = jobClass;
        return this;
    }

    public String getValue() {
        return value;
    }

    public JobType setValue(String value) {
        this.value = value;
        return this;
    }

}

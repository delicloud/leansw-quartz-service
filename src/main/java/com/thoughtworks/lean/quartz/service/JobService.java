package com.thoughtworks.lean.quartz.service;

import com.thoughtworks.lean.quartz.domain.JobCreateDto;
import com.thoughtworks.lean.quartz.domain.JobGetTto;
import com.thoughtworks.lean.quartz.domain.JobDetailDto;

import java.util.List;

/**
 * Created by yongliuli on 7/27/16.
 */
public interface JobService {
    List<JobDetailDto> allJobs();

    void upsertJob(JobCreateDto jobCreateDto);

    void deleteJob(JobGetTto jobDelteTto);

    JobDetailDto getJob(JobGetTto jobGetTto);
}

package com.thoughtworks.lean.quarz.service;

import com.thoughtworks.lean.quarz.domain.JobCreateDto;
import com.thoughtworks.lean.quarz.domain.JobGetTto;
import com.thoughtworks.lean.quarz.domain.JobDetailDto;

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

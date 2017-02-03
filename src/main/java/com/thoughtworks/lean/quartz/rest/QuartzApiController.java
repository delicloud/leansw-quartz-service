package com.thoughtworks.lean.quartz.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.io.Resources;
import com.thoughtworks.lean.quartz.domain.JobCreateDto;
import com.thoughtworks.lean.quartz.domain.JobDetailDto;
import com.thoughtworks.lean.quartz.domain.JobGetTto;
import com.thoughtworks.lean.quartz.service.JobService;
import com.thoughtworks.lean.quartz.util.JSONUtil;
import org.kohsuke.randname.RandomNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.google.common.base.Charsets.UTF_8;

/**
 * Created by yongliuli on 7/27/16.
 */
@RestController
@RequestMapping("/api/quartz")
public class QuartzApiController {

    @Autowired
    JobService jobService;


    RandomNameGenerator rnd = new RandomNameGenerator(0);

    @RequestMapping("random")
    public String hello() {
        return rnd.next();
    }

    @RequestMapping("jobs")
    public List<JobDetailDto> allJobs() {
        return jobService.allJobs();
    }

    @RequestMapping(value = "job", method = RequestMethod.POST)
    public List<JobDetailDto> addJob(@RequestBody JobCreateDto jobCreateDto) {
        jobService.upsertJob(jobCreateDto);
        return jobService.allJobs();
    }

    @RequestMapping(value = "job_exec", method = RequestMethod.POST)
    public List<JobDetailDto> ExecuteJob(@RequestParam String name, @RequestParam String group) {
        JobGetTto jobExecDto = new JobGetTto().setGroup(group).setName(name);
        jobService.executeJob(jobExecDto);
        return jobService.allJobs();
    }

    @RequestMapping(value = "job", method = RequestMethod.DELETE)
    public List<JobDetailDto> delJob(@RequestParam String name, @RequestParam String group) {
        JobGetTto jobDeleteTto = new JobGetTto().setGroup(group).setName(name);
        jobService.deleteJob(jobDeleteTto);
        return jobService.allJobs();
    }

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public List<JobDetailDto> init() throws IOException {
        jobService.init();
        return jobService.allJobs();
    }
}

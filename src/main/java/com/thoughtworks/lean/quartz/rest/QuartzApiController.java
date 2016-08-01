package com.thoughtworks.lean.quartz.rest;

import com.thoughtworks.lean.quartz.domain.JobCreateDto;
import com.thoughtworks.lean.quartz.domain.JobGetTto;
import com.thoughtworks.lean.quartz.domain.JobDetailDto;
import com.thoughtworks.lean.quartz.service.JobService;
import org.kohsuke.randname.RandomNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "job", method = RequestMethod.DELETE)
    public List<JobDetailDto> delJob(@RequestParam String name, @RequestParam String group) {
        JobGetTto jobDeleteTto = new JobGetTto().setGroup(group).setName(name);
        jobService.deleteJob(jobDeleteTto);
        return jobService.allJobs();
    }
}

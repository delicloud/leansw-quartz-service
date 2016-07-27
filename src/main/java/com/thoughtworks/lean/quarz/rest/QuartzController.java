package com.thoughtworks.lean.quarz.rest;

import com.thoughtworks.lean.quarz.domain.JobCreateDto;
import com.thoughtworks.lean.quarz.domain.JobDeleteTto;
import com.thoughtworks.lean.quarz.domain.JobDetailDto;
import com.thoughtworks.lean.quarz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yongliuli on 7/27/16.
 */
@RestController
@RequestMapping("/api/quartz")
public class QuartzController {

    @Autowired
    JobService jobService;

    @RequestMapping("hello")
    public String hello() {
        return "Hello Quartz!";
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
        JobDeleteTto jobDeleteTto = new JobDeleteTto().setGroup(group).setName(name);
        jobService.deleteJob(jobDeleteTto);
        return jobService.allJobs();
    }
}

package com.thoughtworks.lean.quarz.rest;

import com.thoughtworks.lean.quarz.domain.JobCreateDto;
import com.thoughtworks.lean.quarz.domain.JobDetailDto;
import com.thoughtworks.lean.quarz.service.JobService;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("job/add")
    public List<JobDetailDto> addJob(@RequestParam String name, @RequestParam String group, @RequestParam String cron) {
        JobCreateDto jobCreateDto = new JobCreateDto().setCronExpression(cron).setGroup(group).setName(name);

        jobService.addJob(jobCreateDto);
        return jobService.allJobs();
    }
}

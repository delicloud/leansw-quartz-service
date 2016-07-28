package com.thoughtworks.lean.quarz.ui;

import com.thoughtworks.lean.quarz.domain.JobCreateDto;
import com.thoughtworks.lean.quarz.domain.JobGetTto;
import com.thoughtworks.lean.quarz.domain.JobDetailDto;
import com.thoughtworks.lean.quarz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yongliuli on 7/27/16.
 */
@RequestMapping("/ui/quartz")
@Controller
public class QuartzUIController {

    @Autowired
    JobService jobService;

    @RequestMapping("hello")
    public ModelAndView hello() {
        return new ModelAndView("quartz/hello");
    }

    @RequestMapping("jobs")
    public ModelAndView allJobs() {
        return new ModelAndView("quartz/jobs", "jobs", jobService.allJobs());
    }

    @RequestMapping(value = "job/post")
    public List<JobDetailDto> addJob(@RequestBody JobCreateDto jobCreateDto) {
        jobService.upsertJob(jobCreateDto);
        return jobService.allJobs();
    }

    @RequestMapping(value = "job/get")
    public JobDetailDto addJob(@RequestParam String name, @RequestParam String group) {
        JobGetTto jobGetTto = new JobGetTto().setGroup(group).setName(name);
        return jobService.getJob(jobGetTto);
    }

    @RequestMapping(value = "job/delete")
    public void delJob(@RequestParam String name, @RequestParam String group, HttpServletResponse response) throws IOException {
        JobGetTto jobDeleteTto = new JobGetTto().setGroup(group).setName(name);
        jobService.deleteJob(jobDeleteTto);
        response.sendRedirect("/ui/quartz/jobs");
    }

}

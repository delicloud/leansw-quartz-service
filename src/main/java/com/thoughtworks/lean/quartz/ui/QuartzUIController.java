package com.thoughtworks.lean.quartz.ui;

import com.thoughtworks.lean.quartz.domain.JobCreateDto;
import com.thoughtworks.lean.quartz.domain.JobGetTto;
import com.thoughtworks.lean.quartz.domain.JobDetailDto;
import com.thoughtworks.lean.quartz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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


}

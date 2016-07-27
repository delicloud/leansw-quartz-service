package com.thoughtworks.lean.quarz.service.impl;

import com.thoughtworks.lean.quarz.domain.JobCreateDto;
import com.thoughtworks.lean.quarz.domain.JobDetailDto;
import com.thoughtworks.lean.quarz.exception.ServiceErrorException;
import com.thoughtworks.lean.quarz.job.LogJob;
import com.thoughtworks.lean.quarz.service.JobService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yongliuli on 7/27/16.
 */
@Service
public class QuartzServiceImpl implements JobService {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzServiceImpl.class);

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;


    @Override
    public List<JobDetailDto> allJobs() {
        try {
            return getScheduler().getJobKeys(GroupMatcher.anyGroup()).stream().map(jobkey -> {
                try {
                    return getScheduler().getJobDetail(jobkey);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
                return null;
            }).filter(t -> t != null)
                    .map(JobDetailDto::new)
                    .map(
                            jobDetailDto -> {
                                try {
                                    CronTriggerImpl cronTrigger = (CronTriggerImpl) getScheduler().getTrigger(new TriggerKey(jobDetailDto.getName(), jobDetailDto.getGroup()));
                                    jobDetailDto.setCronExpression(cronTrigger.getCronExpression());
                                } catch (SchedulerException e) {
                                    throw new ServiceErrorException(e);
                                }
                                return jobDetailDto;
                            }
                    ).collect(Collectors.toList());
        } catch (SchedulerException e) {
            throw new ServiceErrorException(e);
        }
    }

    private Scheduler getScheduler() {
        return schedulerFactoryBean.getScheduler();
    }

    @Override
    public void addJob(JobCreateDto jobCreateDto) {
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setName(jobCreateDto.getName());
        jobDetail.setGroup(jobCreateDto.getGroup());
        jobDetail.setJobClass(LogJob.class);
        CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setName(jobDetail.getName());
        trigger.setGroup(jobDetail.getGroup());
        trigger.setJobGroup(jobDetail.getGroup());
        try {
            trigger.setCronExpression(jobCreateDto.getCronExpression());
            getScheduler().scheduleJob(jobDetail, trigger);
        } catch (ParseException | SchedulerException e) {
            LOG.error("cron erro!", e);
        }
        //schedulerFactoryBean.getScheduler().scheduleJob()
    }
}

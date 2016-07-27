package com.thoughtworks.lean.quarz.service.impl;

import com.thoughtworks.lean.quarz.domain.JobCreateDto;
import com.thoughtworks.lean.quarz.domain.JobDeleteTto;
import com.thoughtworks.lean.quarz.domain.JobDetailDto;
import com.thoughtworks.lean.quarz.exception.ServiceErrorException;
import com.thoughtworks.lean.quarz.job.JobType;
import com.thoughtworks.lean.quarz.service.JobService;
import org.quartz.JobKey;
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
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
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
    public void deleteJob(JobDeleteTto jobDelteTto) {
        try {
            getScheduler().deleteJob(new JobKey(jobDelteTto.getName(), jobDelteTto.getGroup()));
        } catch (SchedulerException e) {
            throw new ServiceErrorException(e);
        }
    }

    @Override
    public void upsertJob(JobCreateDto jobCreateDto) {

        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setName(jobCreateDto.getName());
        jobDetail.setGroup(jobCreateDto.getGroup());
        jobDetail.setJobClass(JobType.getJobClass(jobCreateDto));
        //
        if (jobCreateDto.getDataMap() != null) {
            for (Map.Entry<String, Object> entry : jobCreateDto.getDataMap().entrySet()) {
                jobDetail.getJobDataMap().put(entry.getKey(), entry.getValue());
            }
        }

        CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setName(jobDetail.getName());
        trigger.setGroup(jobDetail.getGroup());
        trigger.setJobGroup(jobDetail.getGroup());
        try {
            trigger.setCronExpression(jobCreateDto.getCron());
        } catch (ParseException e) {
            throw new ServiceErrorException(e);
        }

        try {
            JobKey jobKey = new JobKey(jobCreateDto.getName(), jobCreateDto.getGroup());
            if (getScheduler().getJobDetail(jobKey) != null) {
                getScheduler().deleteJob(jobKey);
            }
            getScheduler().scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error("schedule cron job error!", e);
        }
        //schedulerFactoryBean.getScheduler().scheduleJob()
    }

}

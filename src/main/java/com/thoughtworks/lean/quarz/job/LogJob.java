package com.thoughtworks.lean.quarz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by yongliuli on 7/27/16.
 */
public class LogJob extends QuartzJobBean {
    private static final Logger LOG = LoggerFactory.getLogger(LogJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("simple job name:" + jobExecutionContext.getJobDetail().getKey().getName() + " group:" + jobExecutionContext.getJobDetail().getKey().getGroup());
    }
}

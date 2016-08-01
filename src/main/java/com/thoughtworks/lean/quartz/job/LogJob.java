package com.thoughtworks.lean.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yongliuli on 7/27/16.
 */
public class LogJob extends AbstractJob {
    private static final Logger LOG = LoggerFactory.getLogger(LogJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("simple job name:" + jobExecutionContext.getJobDetail().getKey().getName() + " group:" + jobExecutionContext.getJobDetail().getKey().getGroup());
    }
}

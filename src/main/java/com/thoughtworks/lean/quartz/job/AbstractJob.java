package com.thoughtworks.lean.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by yongliuli on 7/27/16.
 */
public class AbstractJob extends QuartzJobBean {
    public final static String KEY_JOB_TYPE= "leansw_quartz_job_type";
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}

package com.thoughtworks.lean.quartz.job;

import com.netflix.discovery.EurekaClient;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yongliuli on 7/27/16.
 */
public class ExtAPIJob extends AbstractJob {

    private static final Logger LOG = LoggerFactory.getLogger(LogJob.class);
    private final static String KEY_API_URL = "leansw_quartz_job_api_url";
    RestTemplate extRestTemplate = new RestTemplate();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String apiUrl = jobExecutionContext.getJobDetail().getJobDataMap().getString(KEY_API_URL);
        LOG.info("call ext api:" + apiUrl);
        ResponseEntity<String> ret = extRestTemplate.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(""), String.class);
        LOG.info("response:\n" + ret.getBody());

    }
}

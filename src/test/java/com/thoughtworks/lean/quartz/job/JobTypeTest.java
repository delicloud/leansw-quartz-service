package com.thoughtworks.lean.quartz.job;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yongliuli on 7/27/16.
 */
public class JobTypeTest {
    @Test
    public void testJobType(){
        Class cls= JobType.valueOf("API").getJobClass();
        Assert.assertEquals(cls, APIJob.class);
    }
}

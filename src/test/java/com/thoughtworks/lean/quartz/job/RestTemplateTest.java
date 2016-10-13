package com.thoughtworks.lean.quartz.job;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yongliuli on 10/13/16.
 */
public class RestTemplateTest {

    @Test
    public void test(){
        RestTemplate template=new RestTemplate();
        System.out.println(template.exchange("http://www.baidu.com", HttpMethod.GET,new HttpEntity<>(""),String.class).getBody());

    }
}

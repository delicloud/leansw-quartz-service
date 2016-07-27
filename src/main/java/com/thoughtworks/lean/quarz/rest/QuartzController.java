package com.thoughtworks.lean.quarz.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yongliuli on 7/27/16.
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {
    @RequestMapping("hello")
    public String hello(){
        return "Hello Quartz!";
    }
}

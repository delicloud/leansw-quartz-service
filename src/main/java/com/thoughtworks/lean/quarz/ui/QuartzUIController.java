package com.thoughtworks.lean.quarz.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yongliuli on 7/27/16.
 */
@RequestMapping("/ui/quartz")
@Controller
public class QuartzUIController {

    @RequestMapping("hello")
    public ModelAndView hello(){
        return new ModelAndView("quartz/hello");
    }

}

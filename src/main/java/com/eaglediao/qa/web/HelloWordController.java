package com.eaglediao.qa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWordController {
    @RequestMapping("/test01")
    public ModelAndView test01() {
        System.out.println("Helloword");
        return null;
    }
    @RequestMapping("/test02")
    public ModelAndView test2() {
        System.out.println("Helloword2");
        return null;
    }

}

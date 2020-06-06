package com.itcs6112.oas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @GetMapping("/accessDenied")
    public ModelAndView showAccessDeniedPage(ModelAndView modelAndView) {
        modelAndView.setViewName("accessDenied");
        return modelAndView;
    }
}

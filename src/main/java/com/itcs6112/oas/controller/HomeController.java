package com.itcs6112.oas.controller;

import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private UserInfoService userInfoService;

    @Autowired
    public HomeController(UserInfoService userInfoService) { this.userInfoService = userInfoService; }

    @GetMapping("/")
    public ModelAndView dashboard(ModelAndView modelAndView) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = userInfoService.findByEmail(auth.getName());
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

}

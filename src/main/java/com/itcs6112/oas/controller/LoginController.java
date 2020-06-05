package com.itcs6112.oas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itcs6112.oas.model.UserInfo;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserInfo userInfo) {
		modelAndView.addObject("userInfo", userInfo);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
}

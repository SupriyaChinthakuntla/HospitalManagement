package com.itcs6112.oas.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.service.UserInfoService;

@Controller
public class RegisterController {

	UserInfoService userInfoService;

	@Autowired
	public RegisterController(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserInfo userInfo) {
		modelAndView.addObject("userInfo", userInfo);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid UserInfo userInfo, BindingResult bindingResult, HttpServletRequest request) {
		// Check for existing user
		UserInfo existingUser = userInfoService.findByEmail(userInfo.getEmail());
		if (existingUser != null) {
			modelAndView.addObject("alreadyRegisteredMessage", "There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
		
		// Error in validation
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} 
		// If successful, send to login page to log in
		else {
			userInfo.setRole("patient");
			userInfoService.saveUser(userInfo);
			modelAndView = new ModelAndView("redirect:/login");
			modelAndView.addObject("registrationSuccessMessage", "Patient successfully registered.  Please log in.");
		}
		
		return modelAndView;
	}
	
	
}

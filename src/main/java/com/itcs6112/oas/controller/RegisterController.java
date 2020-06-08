package com.itcs6112.oas.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itcs6112.oas.model.PatientInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.service.PatientInfoService;
import com.itcs6112.oas.service.UserInfoService;

@Controller
public class RegisterController {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private PatientInfoService patientInfoService;
	
	public RegisterController(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	@GetMapping("/register")
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserInfo userInfo) {
		// Check if there is an authenticated used
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// Already authenticated, forward to /
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			modelAndView.setViewName("redirect:/");
		}
		// Not yet authenticated
		else {
			modelAndView.addObject("userInfo", userInfo);
			modelAndView.setViewName("register");
		}

		return modelAndView;
	}
	
	@PostMapping("/register")
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid UserInfo userInfo, BindingResult bindingResult) {
		// Check for existing user
		UserInfo existingUser = userInfoService.findByEmail(userInfo.getEmail());
		if (existingUser != null) {
			modelAndView.setViewName("register");
			bindingResult.rejectValue("email", "error.userInfo", "This email already exists.");
		}
		
		// Error in validation
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} 
		// If successful, send to login page to log in
		else {
			PatientInfo p  = new PatientInfo();		// when we register this way, we are creating patients, create patient object
			p.setDOB(new Date(12,12,1990));			// need to find a way to get the date here
			p.setUserInfo(userInfo);				// associate the newly created user with patient data
			userInfo.setRole("patient");
			userInfoService.saveUser(userInfo);
			patientInfoService.savePatient(p);
			modelAndView.addObject("registrationSuccessMessage", "Patient successfully registered!");
			modelAndView.setViewName("register");
		}
		
		return modelAndView;
	}
	
	
}

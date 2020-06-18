package com.itcs6112.oas.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping("/login")
	public ModelAndView showLoginPage(ModelAndView modelAndView) {
		// Check if there is an authenticated used
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// Already authenticated, forward to /
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			modelAndView.setViewName("redirect:/appointments");
		}
		// Not yet authenticated
		else {
			modelAndView.setViewName("login");
		}

		return modelAndView;
	}
	
}

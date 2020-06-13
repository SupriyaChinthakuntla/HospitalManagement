package com.itcs6112.oas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itcs6112.oas.model.DoctorAvailability;
import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.model.UserInfoPrincipal;
import com.itcs6112.oas.service.DoctorAvailabilityService;

@RestController 
public class DoctorAvailabilityController {
    public ModelAndView modelView;
	
    @Autowired 
    private DoctorAvailabilityService doctorAvailabilityService;
    
    public ModelAndView dashboard(ModelAndView modelAndView) {
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = principal.getUserInfo();
        modelAndView.addObject("user", userInfo);
        modelAndView.setViewName("patientHome");
        modelView = modelAndView;
        return modelAndView;
    }
    
    @GetMapping(path = "/availability/{id}")
    public List<DoctorAvailability> findByDoctorId(@PathVariable Integer id) {
    	 return doctorAvailabilityService.findByDoctorId(id);
    }
    
    @GetMapping(path = "/availability/all")
    public @ResponseBody List<DoctorAvailability> retrieveAllAvailableTime() {
        return doctorAvailabilityService.getAllAvailabilities();
    }
     
}

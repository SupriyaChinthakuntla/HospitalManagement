package com.itcs6112.oas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itcs6112.oas.model.AppointmentInfo;
import com.itcs6112.oas.model.DoctorAvailability;
import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.model.UserInfoPrincipal;
import com.itcs6112.oas.service.DoctorAvailabilityService;
import com.itcs6112.oas.service.DoctorInfoService;


@RestController 
public class DoctorInfoController{
    
    @Autowired 
    private DoctorInfoService doctorInfoService;
    @Autowired 
    private DoctorAvailabilityService doctorAvailabilityService;
    
    public ModelAndView modelView;
    
    @GetMapping(path="/doctors")
    public ModelAndView dashboard(ModelAndView modelAndView) {
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = principal.getUserInfo();
        modelAndView.addObject("user", userInfo);
        modelAndView.setViewName("patientHome");
        modelView = modelAndView;
        return modelAndView;
    }
    
    @ModelAttribute("doctors")
    public List<DoctorInfo> addAttributes(Model model) {
    	return doctorInfoService.getAllDoctors();
    }
    
    @PostMapping(path="/add") 
    public @ResponseBody String addNewDoctor(@RequestBody Map<String, Object> requestBody){
        return doctorInfoService.addNewDoctor(requestBody)? "ADDED NEW DOCTOR" : "ENCOUNTERED ERROR ADDING NEW DOCTOR";
    }
    
    @GetMapping(path = "/doctors/{id}")
    public @ResponseBody Optional<DoctorInfo> searchDoctorById(@PathVariable Integer id) {
        return doctorInfoService.findById(id);
    }
    
    @GetMapping(path = "/doctors/speciality/{speciality}")
    public @ResponseBody List<DoctorInfo> searchDoctorBySpeciality(@PathVariable String speciality) {
        return doctorInfoService.findDoctorsBySpeciality(speciality);
    }

    @GetMapping(path = "/doctors/all")
    public List<DoctorInfo> retrieveAllDoctors() {
        return doctorInfoService.getAllDoctors();
    }
    
    
    @PostMapping("/bookAppointment")
    public ModelAndView scheduleAppointment(AppointmentInfo appointmentInfo, Model model) {
    // DO POST APPOINTMENT HERE
    	return modelView;
    }
    
    @GetMapping(path = "/showAppointments")
    public String showAppointments() {
    	return "redirect:appointmentList";
    }

}
package com.itcs6112.oas.controller;

import java.util.ArrayList;
import java.util.Arrays;

import com.itcs6112.oas.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itcs6112.oas.service.DoctorInfoService;
import com.itcs6112.oas.service.UserInfoService;


@RestController 
public class DoctorInfoController{
    
    @Autowired 
    private DoctorInfoService doctorInfoService;
    
    @Autowired 
    private UserInfoService userInfoService;

    public ModelAndView modelView;
    
    @GetMapping(path="/doctors")
    public ModelAndView dashboard(ModelAndView modelAndView) {
        userInfoService.fetchAllUsers();
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = principal.getUserInfo();
        modelAndView.addObject("docList",new ArrayList<DoctorInfo>());
        modelAndView.addObject("docSpecialtyList",Arrays.asList("Cardiologist", "Neurologist", "Orthopedist"));
        modelAndView.addObject("user", userInfo);
        modelAndView.addObject("appointmentForm", new AppointmentForm());
        modelAndView.setViewName("patientHome");
        modelView = modelAndView;
        return modelAndView;
    }
    
    // @ModelAttribute("doctors")
    // public Iterable<DoctorInfo> addAttributes(Model model) {
    // 	return doctorInfoService.getAllDoctors();
    // }
    
    // @PostMapping(path="/add") 
    // public @ResponseBody String addNewDoctor(@RequestBody Map<String, Object> requestBody){
    //     return doctorInfoService.addNewDoctor(requestBody)? "ADDED NEW DOCTOR" : "ENCOUNTERED ERROR ADDING NEW DOCTOR";
    // }
    
    @GetMapping(path = "/doctors/{id}")
    public @ResponseBody DoctorInfo searchDoctorById(@PathVariable Integer id) {
        return doctorInfoService.findById(id);
    }
    
    @GetMapping(path = "/doctors/speciality/{speciality}")
    public @ResponseBody Iterable<DoctorInfo> searchDoctorBySpeciality(@PathVariable String speciality) {
        System.out.println("\n\n\n");
        System.out.println(speciality);
        // return new ArrayList<DoctorInfo>();
        Iterable<DoctorInfo> l = doctorInfoService.findDoctorsBySpeciality(speciality);
        for (DoctorInfo ld : l)
            System.out.println(ld.getName());
        System.out.println("\n\n\n");
        return doctorInfoService.findDoctorsBySpeciality(speciality);
    } 

    @GetMapping(path = "/doctors/all")
    public Iterable<DoctorInfo> retrieveAllDoctors() {
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
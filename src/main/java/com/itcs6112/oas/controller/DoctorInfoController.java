package com.itcs6112.oas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.model.UserInfoPrincipal;
import com.itcs6112.oas.service.DoctorInfoService;


@Controller 
@RequestMapping(path = "/doctors") 
public class DoctorInfoController{
    
    @Autowired 
    private DoctorInfoService doctorInfoService;
    
    @GetMapping(path = "") 
    public ModelAndView dashboard(ModelAndView modelAndView, Model model) {
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = principal.getUserInfo();
        modelAndView.addObject("user", userInfo);
        modelAndView.setViewName("patientHome");
         List<DoctorInfo> doc = new ArrayList<>();
         doc = this.retrieveAllDoctors(model);
     	model.addAttribute("doctors", doctorInfoService.getAllDoctors());
         System.out.print(doc.toString());
        return modelAndView;
    }
    
    @PostMapping(path="/add") 
    public @ResponseBody String addNewDoctor(@RequestBody Map<String, Object> requestBody){
        return doctorInfoService.addNewDoctor(requestBody)? "ADDED NEW DOCTOR" : "ENCOUNTERED ERROR ADDING NEW DOCTOR";
    }
    
    @GetMapping(path = "/{search}")
    public @ResponseBody Optional<DoctorInfo> searchDoctorById(@RequestBody Map<String, Object> requestBody) {
        return doctorInfoService.findById((Integer) requestBody.get("id"));
    }
    
    @GetMapping(path = "/{search_specialty}")
    public @ResponseBody Optional<Iterable<DoctorInfo>> searchDoctorBySpecialty(@RequestBody Map<String, Object> requestBody) {
        return doctorInfoService.findDoctorsBySpecialty((String) requestBody.get("specialty"));
    }

    @GetMapping(path = "/all")
    public List<DoctorInfo> retrieveAllDoctors(Model model) {
    	System.out.println(doctorInfoService.getAllDoctors().toString());
        return doctorInfoService.getAllDoctors();
    }
    
//    public List<String> getAllSpecialities() {
//    	List<DoctorInfo> doctors = this.retrieveAllDoctors();
//    	List<String> specialities =  new ArrayList<>();
//    
//    	doctors.stream().map(DoctorInfo::getName)
//        .collect(Collectors.toList());
//    	    	
//    	return specialities;
//    	
//    }



}
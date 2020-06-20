package com.itcs6112.oas.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itcs6112.oas.model.DoctorAvailability;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.model.UserInfoPrincipal;
import com.itcs6112.oas.service.DoctorAvailabilityService;
import com.itcs6112.oas.service.DoctorInfoService;

@RestController 
public class DoctorAvailabilityController {
    public ModelAndView modelView;
	
    @Autowired 
    private DoctorAvailabilityService doctorAvailabilityService;
    @Autowired 
    private DoctorInfoService doctorInfoService;

    
    public ModelAndView dashboard(ModelAndView modelAndView) {

        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = principal.getUserInfo();
        modelAndView.addObject("docSpecialtyList",Arrays.asList("Cardiologist", "Neurologist", "Orthopedist","Radiologist","Pediatrician"));
        modelAndView.addObject("user", userInfo);
        modelAndView.addObject("doctorInfoService", doctorInfoService);
        modelAndView.setViewName("patientHome");
        modelView = modelAndView;
        return modelAndView;
    }
    
    @GetMapping(path = "/availability/{id}")
    public List<DoctorAvailability> findByDoctorId(@PathVariable Integer id) {
        System.out.println("\n\n\n");
        System.out.println("DoctorAvailabilityController");
        List<DoctorAvailability> d = doctorAvailabilityService.findByDoctorId(id);
        for (DoctorAvailability da : d) 
            System.out.println(da.getDoctorAvailableTime());
        return doctorAvailabilityService.findByDoctorId(id);
    }
    
    @GetMapping(path = "/availability/all")
    public @ResponseBody Iterable<DoctorAvailability> retrieveAllAvailableTime() {
        return doctorAvailabilityService.getAllAvailabilities();
    }
     
}

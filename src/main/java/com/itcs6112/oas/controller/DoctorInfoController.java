package com.itcs6112.oas.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.service.DoctorInfoService;

@Controller 
@RequestMapping(path = "/doctors") 
public class DoctorInfoController{
    
    @Autowired 
    private DoctorInfoService doctorInfoService;
    
    @PostMapping(path="/add") 
    public @ResponseBody String addNewDoctor(@RequestBody Map<String, Object> requestBody){
        return doctorInfoService.addNewDoctor(requestBody)? "ADDED NEW DOCTOR" : "ENCOUNTERED ERROR ADDING NEW DOCTOR";
    }
    
    @GetMapping(path = "/search")
    public @ResponseBody Optional<DoctorInfo> searchDoctorById(@RequestBody Map<String, Object> requestBody) {
        return doctorInfoService.findById((Integer) requestBody.get("id"));
    }
    
    @GetMapping(path = "/search_specialty")
    public @ResponseBody Optional<Iterable<DoctorInfo>> searchDoctorBySpecialty(@RequestBody Map<String, Object> requestBody) {
        return doctorInfoService.findDoctorsBySpecialty((String) requestBody.get("specialty"));
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<DoctorInfo> retrieveAllDoctors() {
        return doctorInfoService.getAllDoctors();
    }



}
package com.itcs6112.oas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itcs6112.oas.model.PatientInfo;
import com.itcs6112.oas.service.PatientInfoService;

@Controller 
@RequestMapping(path = "/patients") 
public class PatientInfoController {
    
    @Autowired 
    private PatientInfoService patientInfoService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<PatientInfo> retrieveAllPatients() {
        return patientInfoService.getAllPatients();
    }
}
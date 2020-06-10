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

import com.itcs6112.oas.model.PatientInfo;
import com.itcs6112.oas.service.PatientInfoService;

@Controller 
@RequestMapping(path = "/patients") 
public class PatientInfoController {
    
    @Autowired 
    private PatientInfoService patientInfoService;
    
    // @PostMapping(path="/add") 
    // public @ResponseBody String addNewPatient(@RequestBody Map<String, Object> requestBody){
    //     return patientInfoService.addNewPatient(requestBody)? "ADDED NEW PATIENT" : "ENCOUNTERED ERROR ADDING NEW PATIENT";
    // }
    
    // @GetMapping(path = "/search")
    // public @ResponseBody Optional<PatientInfo> searchPatientById(@RequestBody Map<String, Object> requestBody) {
    //     return patientInfoService.findById((Integer) requestBody.get("id"));
    // }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<PatientInfo> retrieveAllPatients() {
        return patientInfoService.getAllPatients();
    }



}
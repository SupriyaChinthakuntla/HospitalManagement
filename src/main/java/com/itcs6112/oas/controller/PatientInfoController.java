package com.itcs6112.oas.controller;

import java.sql.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itcs6112.oas.model.PatientInfo;
import com.itcs6112.oas.repository.PatientInfoRepository;

@Controller 
@RequestMapping(path = "/patients") 
public class PatientInfoController {
    
    @Autowired 
    private PatientInfoRepository patientRepository;


    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewPatient(@RequestBody Map<String, Object> request_body){
        String name = (String) request_body.get("name");
        int date_int = (int) request_body.get("date_int");
        Date date = new Date(date_int);
        PatientInfo n = new PatientInfo();
        n.setDOB(date);
        patientRepository.save(n);
        return name + "SUCCESSFULLY ADDED PATIENT_INFO";
    }
    

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<PatientInfo> getAllUsers() {
        return patientRepository.findAll();
    }

    // private void parse_post_request(Map<String,Object>request_body){
        
    // }

}
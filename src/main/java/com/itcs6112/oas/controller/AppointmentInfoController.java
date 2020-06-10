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

import com.itcs6112.oas.model.AppointmentInfo;
import com.itcs6112.oas.service.AppointmentInfoService;

@Controller 
@RequestMapping(path = "/appointments") 
public class AppointmentInfoController{
    
    @Autowired 
    private AppointmentInfoService appointmentInfoService;
    
    // @PostMapping(path="/add") 
    // public @ResponseBody String addNewDoctor(@RequestBody Map<String, Object> requestBody){
    //     return appointmentInfoService.addNewAppointment(requestBody)? "ADDED NEW Appointment" : "ENCOUNTERED ERROR ADDING NEW Appointment";
    // }
    
    @GetMapping(path = "/search")
    public @ResponseBody Optional<AppointmentInfo> searchDoctorById(@RequestBody Map<String, Object> requestBody) {
        return appointmentInfoService.findById((Integer) requestBody.get("id"));
    }
    
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<AppointmentInfo> retrieveAllDoctors() {
        return appointmentInfoService.getAllAppointments();
    }



}
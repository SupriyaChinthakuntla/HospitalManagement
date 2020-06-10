package com.itcs6112.oas.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itcs6112.oas.model.AppointmentInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.model.UserInfoPrincipal;
import com.itcs6112.oas.service.AppointmentInfoService;

@RestController 
public class AppointmentInfoController {
    
    @Autowired 
    private AppointmentInfoService appointmentInfoService;
    
	@GetMapping("/appointments")
    public ModelAndView dashboard(ModelAndView modelAndView) {
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = principal.getUserInfo();
        modelAndView.addObject("patientId", userInfo.getId());
        modelAndView.setViewName("appointmentList");
        this.appointmentsList(userInfo.getId());
        return modelAndView;
    }
    
    
    @PostMapping(path="/appointments/add") 
    public @ResponseBody String addNewDoctor(@RequestBody Map<String, Object> requestBody){
        return appointmentInfoService.addNewAppointment(requestBody)? "ADDED NEW Appointment" : "ENCOUNTERED ERROR ADDING NEW Appointment";
    }
    
    @GetMapping(path = "/appointments/search")
    public @ResponseBody Optional<AppointmentInfo> searchDoctorById(@RequestBody Map<String, Object> requestBody) {
        return appointmentInfoService.findById((Integer) requestBody.get("id"));
    }
    
    @GetMapping(path = "/appointments/all")
    public @ResponseBody List<AppointmentInfo> retrieveAllAppointments() {
        return appointmentInfoService.findAll();
    }
    
    
    @GetMapping(path = "/appointments/{id}")
    public @ResponseBody List<AppointmentInfo> retrieveAllAppointmentsById(@PathVariable Integer id) {
    	System.out.print("ssssssss*****" + id);
    	System.out.print(appointmentInfoService.findByPatientId(id));
        return appointmentInfoService.findByPatientId(id);
    }


    @ModelAttribute("appointmentsList")
    public List<AppointmentInfo> appointmentsList(Integer id) {
    	return this.retrieveAllAppointmentsById(28);
    }


}
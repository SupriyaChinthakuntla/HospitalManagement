package com.itcs6112.oas.controller;

import java.sql.Timestamp;
import java.util.Arrays;

import com.itcs6112.oas.model.AppointmentInfo;
import com.itcs6112.oas.model.DoctorAvailability;
import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.service.AppointmentInfoService;
import com.itcs6112.oas.service.DoctorAvailabilityService;
import com.itcs6112.oas.service.DoctorInfoService;
import com.itcs6112.oas.service.PatientInfoService;
import com.itcs6112.oas.service.UserInfoService;
import com.itcs6112.oas.model.UserInfoPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    @Autowired
    UserInfoService userInfoService;
    
    @Autowired
    DoctorInfoService doctorInfoService;
    
    @Autowired
    PatientInfoService patientInfoService;
    
    @Autowired
    AppointmentInfoService appointmentInfoService;
    
    @Autowired
    DoctorAvailabilityService doctorAvailabilityService;

    private UserInfo currentUserInfo = null;


    
    @GetMapping(path="/")
    public ModelAndView showHome(ModelAndView modelAndView) {
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getUserInfo().getRole().equals("admin"))
            return new ModelAndView("redirect:/admin", modelAndView.getModel());
        else
            return new ModelAndView("redirect:/appointments", modelAndView.getModel());
    }

    @GetMapping("/admin")
    public ModelAndView showDashboard(ModelAndView modelAndView, @RequestParam(required=false) boolean success) {

        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getUserInfo().getRole().equals("admin")) {
        	if (success) {
        		modelAndView.addObject("doctorAddedSuccessfullyMsg", "Doctor created successfully!");
        	}
            refreshDashboard(modelAndView);
        } else{
            return new ModelAndView("redirect:/appointments", modelAndView.getModel());

        }
        return modelAndView;
    }

    @PostMapping("/createDoctor")
    public ModelAndView createDoctorSubmit(ModelAndView modelAndView, @ModelAttribute UserInfo userInfo) {
        userInfoService.saveUser(userInfo);
        DoctorInfo d = new DoctorInfo();
        d.setSpecialty(userInfo.getTesting()); // using password data field as placeholder to get the doctor specialty
        d.setName(userInfo.getFname() + " " + userInfo.getLname());
        d.setUserInfoId(userInfo.getId()); // associate this new doctor with UserInfo object populated by thymeleaf form
        userInfo.setRole("doctor"); // set this user's role to doctor
        userInfo.setPassword("defaultpassword"); // set the password to a default (user would change at later date)

        doctorInfoService.saveDoctor(d);
        
        DoctorAvailability da = new DoctorAvailability();
        da.setDoctorAvailableTime(new Timestamp(System.currentTimeMillis()));
        da.setDoctorId(d.getId());
        doctorAvailabilityService.saveAvailability(da);
        return new ModelAndView("redirect:/admin?success=true", modelAndView.getModel());
    }

    private ModelAndView refreshDashboard(ModelAndView modelAndView){
        this.doctorInfoService.fetchAllDoctors();
        this.patientInfoService.fetchAllPatients();
        this.userInfoService.fetchAllUsers();
        this.appointmentInfoService.fetchAllAppointments();
        modelAndView.addObject("docSpecialtyList",Arrays.asList("Cardiologist", "Neurologist", "Orthopedist"));
        modelAndView.addObject("docInfoService",this.doctorInfoService);
        modelAndView.addObject("apptInfoService",this.appointmentInfoService);
        modelAndView.addObject("patInfoService",this.patientInfoService);
        modelAndView.addObject("userInfoService",this.patientInfoService);
        modelAndView.addObject("currentUserInfo", this.currentUserInfo);
        modelAndView.addObject("dummyUserInfo", new UserInfo());
        modelAndView.addObject("dummyApptInfo", new AppointmentInfo());
        modelAndView.setViewName("adminDashboard");
        return modelAndView; 
    }

}

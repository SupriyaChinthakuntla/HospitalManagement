package com.itcs6112.oas.controller;

import java.util.Map;

import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.model.PatientInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.service.DoctorInfoService;
import com.itcs6112.oas.service.PatientInfoService;
import com.itcs6112.oas.service.UserInfoService;
import com.itcs6112.oas.model.UserInfoPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController{
    @Autowired 
    UserInfoService userInfoService;
    @Autowired 
    DoctorInfoService doctorInfoService;
    @Autowired 
    PatientInfoService patientInfoService;

    private UserInfo currentUserInfo = null;

    @RequestMapping("/")
    public ModelAndView showDashboard(ModelAndView modelAndView) {
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal.getUserInfo().getRole().equals("admin")){
            refreshDashboard(modelAndView);
        }
        else
            modelAndView.setViewName("userDashboard");
        return modelAndView;
    }

    @PostMapping("/createDoctor")
    public ModelAndView createDoctorSubmit(ModelAndView modelAndView, @ModelAttribute UserInfo userInfo){ 
        DoctorInfo d = new DoctorInfo();
        d.setSpecialty(userInfo.getPassword());         // using password data field as placeholder to get the doctor specialty in
        d.setUserInfo(userInfo);                        // associate this new doctor with UserInfo object populated by thymeleaf form
        userInfo.setRole("doctor");                     // set this user's role to doctor
        userInfo.setPassword("defaultpassword");        // set the password to a default (user would change at later date) 
        
        userInfoService.saveUser(userInfo);
        doctorInfoService.saveDoctor(d);
        
        return this.refreshDashboard(modelAndView);
    }

    private ModelAndView refreshDashboard(ModelAndView modelAndView){
        modelAndView.addObject("allPatients",this.patientInfoService.getAllPatients());
        modelAndView.addObject("allDoctors",this.doctorInfoService.getAllDoctors());
        for(PatientInfo u: this.patientInfoService.getAllPatients()){
            System.out.println(u.getUserInfo().getEmail());
        }
        modelAndView.addObject("allUsers",this.userInfoService.getAllUsers());
        modelAndView.addObject("currentUserInfo", this.currentUserInfo);
        modelAndView.addObject("dummyUserInfo", new UserInfo());
        modelAndView.setViewName("adminDashboard");
        return modelAndView; 
    }

}

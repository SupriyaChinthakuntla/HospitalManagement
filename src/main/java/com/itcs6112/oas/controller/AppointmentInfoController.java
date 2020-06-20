package com.itcs6112.oas.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import com.itcs6112.oas.model.AppointmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.itcs6112.oas.model.AppointmentInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.model.UserInfoPrincipal;
import com.itcs6112.oas.service.AppointmentInfoService;
import com.itcs6112.oas.service.DoctorInfoService;
import com.itcs6112.oas.service.PatientInfoService;
import com.itcs6112.oas.service.UserInfoService;

import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;

import com.itcs6112.oas.Mailer;

@RestController
public class AppointmentInfoController {

    @Autowired
    private AppointmentInfoService appointmentInfoService;
   
    @Autowired
    private DoctorInfoService docInfoService;
    
    @Autowired
    private PatientInfoService patientInfoService;
    
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/appointments")
    public ModelAndView dashboard(ModelAndView modelAndView) {
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        docInfoService.fetchAllDoctors();
        userInfoService.fetchAllUsers();
        modelAndView.addObject("docInfoService", this.docInfoService);
        modelAndView.addObject("patientInfoService", this.patientInfoService);
        if (principal.getUserInfo().getRole().equals("doctor")){
            Integer docId = this.docInfoService.findByUserInfoId(principal.getUserInfo().getId()).getId();
            modelAndView.addObject("appointments_future", appointmentInfoService.findByDoctorId_future(docId)); 
            modelAndView.addObject("appointments_past", appointmentInfoService.findByDoctorId_past(docId));
            modelAndView.addObject("appointments", appointmentInfoService.findByDoctorId(docId));
            modelAndView.setViewName("appointmentListDoctor");
        }
        else if (principal.getUserInfo().getRole().equals("admin")){
            return new ModelAndView("redirect:/admin", modelAndView.getModel());
        }
        else{
            modelAndView.addObject("appointments_future", appointmentInfoService.findByPatientId_future(principal.getUserInfo().getId())); 
            modelAndView.addObject("appointments_past", appointmentInfoService.findByPatientId_past(principal.getUserInfo().getId())); 
            modelAndView.addObject("appointments", appointmentInfoService.findByPatientId(principal.getUserInfo().getId()));
            modelAndView.setViewName("appointmentList");
        }
        return modelAndView;
    }

    @PostMapping("/appointments")
    public ModelAndView newAppointment(@ModelAttribute("appointmentInfo") AppointmentForm appointmentForm, ModelAndView modelAndView, BindingResult bindingResult) throws ParseException {
    	UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date d2 = df2.parse(appointmentForm.getDoctorAvailableTime());
        AppointmentInfo appointmentInfo = new AppointmentInfo();

        if (appointmentForm.getPatientId() != null){
            appointmentInfo = new AppointmentInfo(appointmentForm.getPatientId(), appointmentForm.getDoctorId(), appointmentForm.getReasonForVisit(), d2);
        }
        else{
            appointmentInfo = new AppointmentInfo(principal.getUserInfo().getId(), appointmentForm.getDoctorId(), appointmentForm.getReasonForVisit(), d2);
        }

        if (!bindingResult.hasErrors()) {
             Mailer.send(principal.getUserInfo(), appointmentInfo); 
        } 
        
        appointmentInfoService.createAppointment(appointmentInfo);
    	modelAndView.addObject("appointments", appointmentInfoService.findByPatientId(principal.getUserInfo().getId()));
    	modelAndView.addObject("docInfoService", this.docInfoService);
        
        if (principal.getUserInfo().getRole().equals("admin")){
            return new ModelAndView("redirect:/admin", modelAndView.getModel());
        }
        else
            return new ModelAndView("redirect:/appointments", modelAndView.getModel());

    }

    @GetMapping("/appointments/{id}/cancel")
    public RedirectView cancelAppointment(@PathVariable Integer id, HttpServletRequest request) {
        appointmentInfoService.cancelAppointment(id);
        String referer = request.getHeader("Referer");
        return new RedirectView(referer);
    }

}


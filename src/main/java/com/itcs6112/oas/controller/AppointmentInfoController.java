package com.itcs6112.oas.controller;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;
import com.itcs6112.oas.model.AppointmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.itcs6112.oas.model.AppointmentInfo;
import com.itcs6112.oas.model.UserInfoPrincipal;
import com.itcs6112.oas.service.AppointmentInfoService;
import com.itcs6112.oas.service.DoctorInfoService;
import com.itcs6112.oas.service.UserInfoService;

import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AppointmentInfoController {

    @Autowired
    private AppointmentInfoService appointmentInfoService;
    @Autowired
    private DoctorInfoService docInfoService;
    
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/appointments")
    public ModelAndView dashboard(ModelAndView modelAndView) {
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userInfoService.fetchAllUsers();
        modelAndView.addObject("appointments", appointmentInfoService.findByPatientId(principal.getUserInfo().getId()));
        modelAndView.addObject("docInfoService", this.docInfoService);
        modelAndView.setViewName("appointmentList");

        return modelAndView;
    }

    @PostMapping("/appointments")
    public ModelAndView newAppointment(@ModelAttribute("appointmentInfo") AppointmentForm appointmentForm, ModelAndView modelAndView) throws ParseException {
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 2020-06-06T12:00:00.000+00:00
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(appointmentForm.getDoctorAvailableTime());
        Date date = Date.from(zonedDateTime.toInstant());

        AppointmentInfo appointmentInfo = new AppointmentInfo(principal.getUserInfo().getId(), appointmentForm.getDoctorId(), appointmentForm.getReasonForVisit(), date);
        System.out.println("ATTEMPTING TO SAVE") ;
        appointmentInfoService.createAppointment(appointmentInfo);
        modelAndView.addObject("appointments", appointmentInfoService.findByPatientId(principal.getUserInfo().getId()));
        modelAndView.addObject("docInfoService", this.docInfoService);
        modelAndView.setViewName("appointmentList");
        return modelAndView;
    }

    @GetMapping("/appointments/{id}/cancel")
    public RedirectView cancelAppointment(@PathVariable Integer id, HttpServletRequest request) {
        appointmentInfoService.cancelAppointment(id);
        String referer = request.getHeader("Referer");
        return new RedirectView(referer);
    }

}


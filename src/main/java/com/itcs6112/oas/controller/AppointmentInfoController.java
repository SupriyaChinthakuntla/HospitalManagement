package com.itcs6112.oas.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import java.util.Map;
import java.util.Optional;

import com.itcs6112.oas.model.AppointmentForm;
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
import com.itcs6112.oas.service.DoctorInfoService;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AppointmentInfoController {

    @Autowired
    private AppointmentInfoService appointmentInfoService;
    @Autowired
    private DoctorInfoService docInfoService;

    @GetMapping("/appointments")
    public ModelAndView dashboard(ModelAndView modelAndView) {
        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        appointmentInfoService.createAppointment(appointmentInfo);

        modelAndView.addObject("appointments", appointmentInfoService.findByPatientId(principal.getUserInfo().getId()));
        modelAndView.addObject("docInfoService", this.docInfoService);
        modelAndView.setViewName("appointmentList");
        return modelAndView;
    }


//    @PostMapping(path = "/appointments/add")
//    public @ResponseBody
//    String addNewDoctor(@RequestBody Map<String, Object> requestBody) {
//        System.out.println("Hello");
//        return appointmentInfoService.addNewAppointment(requestBody) ? "ADDED NEW Appointment" : "ENCOUNTERED ERROR ADDING NEW Appointment";
//    }

//    @GetMapping(path = "/appointments/search")
//    public @ResponseBody
//    Optional<AppointmentInfo> searchDoctorById(@RequestBody Map<String, Object> requestBody) {
//        return appointmentInfoService.findById((Integer) requestBody.get("id"));
//    }

//    @GetMapping(path = "/appointments/all")
//    public @ResponseBody
//    List<AppointmentInfo> retrieveAllAppointments() {
//        return appointmentInfoService.findAll();
//    }


//    @GetMapping(path = "/appointments/{id}")
//    public @ResponseBody
//    List<AppointmentInfo> retrieveAllAppointmentsById(@PathVariable Integer id) {
//        return appointmentInfoService.findByPatientId(id);
//    }

    @GetMapping("/appointments/{id}/cancel")
    public RedirectView cancelAppointment(@PathVariable Integer id, HttpServletRequest request) {
        appointmentInfoService.cancelAppointment(id);
        String referer = request.getHeader("Referer");
        return new RedirectView(referer);
    }

//    @ModelAttribute("appointmentsList")
//    public List<AppointmentInfo> appointmentsList(Integer id) {
//        UserInfoPrincipal principal = (UserInfoPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return this.retrieveAllAppointmentsById(principal.getUserInfo().getId());
//    }


}


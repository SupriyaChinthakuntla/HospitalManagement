package com.itcs6112.oas.service;

import com.itcs6112.oas.model.AppointmentInfo;
import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.repository.AppointmentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentInfoService{

    @Autowired
    private AppointmentInfoRepository appointmentInfoRepository;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private DoctorInfoService doctorInfoService;
   
    private Iterable<AppointmentInfo> appointments;
    public AppointmentInfoService(AppointmentInfoRepository appointmentInfoRepository){
        this.appointmentInfoRepository = appointmentInfoRepository;
    }
    
    public String getInfoString(AppointmentInfo appt){
        DoctorInfo doctorInfo = this.doctorInfoService.findById(appt.getDoctorId());
        UserInfo userInfo_1 = this.userInfoService.findById(doctorInfo.getUserInfoId());
        // PatientInfo patientInfo = this.patientInfoService.findById(appt.getPatientId());
        UserInfo userInfo_2 = this.userInfoService.findById(appt.getPatientId());// not quite to schema
        
        String doc_name = userInfo_1.getFname() + " " + userInfo_1.getLname();
        String pat_name = userInfo_2.getFname() + " " + userInfo_2.getLname();
        // String pat_name = "WHY CANT I GET THIS TO WORK";
        String date = appt.getStartDate().toString();
        String reason = appt.getReasonForVisit();
        return String.format("Doctor: %s | Patient: %s | Appt Date: %s | Reason: %s",doc_name,pat_name,date,reason);

    }
    
    public String getPatientName(AppointmentInfo appt){
        // PatientInfo patientInfo = this.patientInfoService.findById(appt.getDoctorId());
        UserInfo userInfo= this.userInfoService.findById(appt.getPatientId());
		return userInfo != null ? userInfo.getFname() + " " + userInfo.getLname(): "N/A";
    }
    
    public String getDoctorName(AppointmentInfo appt){
        DoctorInfo doctorInfo = this.doctorInfoService.findById(appt.getDoctorId());
        UserInfo userInfo= this.userInfoService.findById(doctorInfo.getUserInfoId());
		return userInfo != null ? userInfo.getFname() + " " + userInfo.getLname(): "N/A";
    }
    
    public String getReason(AppointmentInfo appt){
		return appt.getReasonForVisit();
    }

    public String getStartDate(AppointmentInfo doc){
        return doc.getStartDate().toString();
    }
    public String getEndDate(AppointmentInfo doc){
        return doc.getEndDate().toString();
    }

    public AppointmentInfo createAppointment(AppointmentInfo appointmentInfo) {
        return appointmentInfoRepository.save(appointmentInfo);
    }

    public void cancelAppointment(Integer id) { appointmentInfoRepository.deleteById(id); }
    
    public Optional<AppointmentInfo> findById(Integer id) {
        return appointmentInfoRepository.findById(id);
    }
    
    public void fetchAllAppointments() {
        this.appointments = appointmentInfoRepository.findAll();
    }
    
    public Iterable<AppointmentInfo> findByPatientId(Integer id) {
        return appointmentInfoRepository.findByPatientId(id);
    }

    public Iterable<AppointmentInfo> getAllAppointments() {
        return this.appointments;
    }
    public Iterable<AppointmentInfo> findAll() {
    	List<AppointmentInfo> appointmentList = new ArrayList<>();  
    	appointmentInfoRepository.findAll().forEach(appointmentList::add);
        return appointmentList;
    }

    public void saveAppointment(AppointmentInfo user) {
        appointmentInfoRepository.save(user);
    }
    
}

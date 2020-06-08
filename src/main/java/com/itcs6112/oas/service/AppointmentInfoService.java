package com.itcs6112.oas.service;

import com.itcs6112.oas.model.AppointmentInfo;
import com.itcs6112.oas.repository.AppointmentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class AppointmentInfoService{

    @Autowired
    private AppointmentInfoRepository appointmentInfoRepository;
    
    public AppointmentInfoService(AppointmentInfoRepository appointmentInfoRepository){
        this.appointmentInfoRepository = appointmentInfoRepository;
    }

    public boolean addNewAppointment(Map<String,Object> requestBody){
        return createNewAppointment(requestBody);
    }
    
    public Optional<AppointmentInfo> findById(Integer id) {
        return appointmentInfoRepository.findById(id);
    }

    public Iterable<AppointmentInfo> getAllAppointments() {
        return appointmentInfoRepository.findAll();
    }

    public void saveAppointment(AppointmentInfo user) {
        appointmentInfoRepository.save(user);
    }
    
    // returns true if a new user is successfully created and added to the database
    private boolean createNewAppointment(Map<String,Object>requestBody){
        // check to see if the request is properly formed, and create new user
        // Minimal error checking done
        if (checkNewApptRequest(requestBody)){
            AppointmentInfo a = new AppointmentInfo();
            a.setApptNotes((String) requestBody.get("appt_notes")); 
            a.setCancelled((Boolean) requestBody.get("cancelled"));
            a.setStartDate((Date) requestBody.get("date_start"));
            a.setEndDate((Date) requestBody.get("date_end"));
            a.setDoctorId((Integer) requestBody.get("doctor_id")); 
            a.setPatientId((Integer) requestBody.get("patient_id")); 
            saveAppointment(a);
            System.out.println(a);
            return true;
        }
        return false;
    }
    // helper function to determine if new user request has all data fields
    private boolean checkNewApptRequest(Map<String,Object>requestBody){
        String [] l = {"patient_id","doctor_id","appt_notes","reason_for_visit","cancelled","date_start","date_end"};
        for (String k : l)
            if(!requestBody.containsKey(k))
                return false;
        return true;
    }
}

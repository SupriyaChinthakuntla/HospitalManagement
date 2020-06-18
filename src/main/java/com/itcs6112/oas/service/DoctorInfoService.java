package com.itcs6112.oas.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.repository.DoctorInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DoctorInfoService{

    @Autowired
    private DoctorInfoRepository doctorInfoRepository;
    @Autowired
    private UserInfoService userInfoService;
    
    private Iterable<DoctorInfo> doctors;

    public DoctorInfoService(DoctorInfoRepository doctorInfoRepository){
        this.doctorInfoRepository = doctorInfoRepository; 
    }
    
    public void fetchAllDoctors(){
        this.doctors = doctorInfoRepository.findAll();
    }
    
    public Iterable<DoctorInfo> getAllDoctors(){
        return this.doctors;
    }
   
    public DoctorInfo findById(Integer ID){
        for(DoctorInfo d : doctors)
            if(d.getId().equals(ID))
                return d;
        return doctorInfoRepository.findById(ID).orElse(null);
    }
    
    public Iterable<DoctorInfo> findDoctorsBySpeciality(String specialty){
        return doctorInfoRepository.findBySpecialty(specialty);
    }
    
    public void saveDoctor(DoctorInfo doctor) {
        doctorInfoRepository.save(doctor);
    }
    
    public String getDoctorName(DoctorInfo doc){
        UserInfo userInfo = this.userInfoService.findById(doc.getUserInfoId());
        return userInfo != null ? userInfo.getFname() + " " + userInfo.getLname(): "N/A";
    }

	public String getDoctorEmail(DoctorInfo doc){
        UserInfo userInfo = this.userInfoService.findById(doc.getUserInfoId());
		return userInfo != null ? userInfo.getEmail() : "N/A";
    }
	public String getDoctorSpecialty(DoctorInfo doc){
		return doc.getSpeciality();
    }
  
	// public String getInfoString(Integer id){
	public String getInfoString(DoctorInfo doc){
        UserInfo userInfo = this.userInfoService.findById(doc.getUserInfoId());
        return String.format("Doctor: %s %s | Email: %s | Specialty: %s", userInfo.getFname(),userInfo.getLname(),userInfo.getEmail(), doc.getSpeciality());
    }
}
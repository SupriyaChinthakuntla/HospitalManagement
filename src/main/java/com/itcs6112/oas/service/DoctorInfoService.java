package com.itcs6112.oas.service;

import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.repository.DoctorInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class DoctorInfoService{

    @Autowired
    private DoctorInfoRepository doctorInfoRepository;
    @Autowired
    private UserInfoService userInfoService;
    
    public DoctorInfoService(DoctorInfoRepository doctorInfoRepository){
        this.doctorInfoRepository = doctorInfoRepository; 
    }
    
    public Iterable<DoctorInfo> getAllDoctors(){
        return doctorInfoRepository.findAll();
    }
   
    public DoctorInfo findById(Integer ID){
        return doctorInfoRepository.findById(ID).orElse(null);
    }
    
    public Optional<Iterable<DoctorInfo>> findDoctorsBySpecialty(String specialty){
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
		return doc.getSpecialty();
    }
  
	// public String getInfoString(Integer id){
	public String getInfoString(DoctorInfo doc){
        UserInfo userInfo = this.userInfoService.findById(doc.getUserInfoId());
		return userInfo != null ? userInfo.getFname() + " " + userInfo.getLname() + " " + userInfo.getEmail() + ": " + doc.getSpecialty() : "N/A";
    }
}

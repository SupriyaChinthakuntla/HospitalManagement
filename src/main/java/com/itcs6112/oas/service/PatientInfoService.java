package com.itcs6112.oas.service;

import com.itcs6112.oas.model.PatientInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.repository.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
import java.sql.Date;

@Service
public class PatientInfoService {

    @Autowired
    private PatientInfoRepository patientInfoRepository;
    
    @Autowired
    private UserInfoService userInfoService;
    
    public PatientInfoService(PatientInfoRepository patientInfoRepository) {
        this.patientInfoRepository = patientInfoRepository;
    }
    public Iterable<PatientInfo> getAllPatients(){
        return patientInfoRepository.findAll();
    }
    public PatientInfo findById(Integer ID){
        return patientInfoRepository.findById(ID).orElse(null);
    }
    public void savePatient(PatientInfo patient) {
        patientInfoRepository.save(patient);
    }
    public String getPatientName(PatientInfo patient){
        UserInfo userInfo = this.userInfoService.findById(patient.getUserInfoId());
		return userInfo != null ? userInfo.getFname() + " " + userInfo.getLname(): "N/A";
    }

	public String getPatientEmail(PatientInfo patient){
        UserInfo userInfo = this.userInfoService.findById(patient.getUserInfoId());
		return userInfo != null ? userInfo.getEmail() : "N/A";
    }
	public String getPatientDOB(PatientInfo patient){
		return patient.getDOB().toString();
    }
    
    public String getInfoString(PatientInfo pat){
        UserInfo userInfo = this.userInfoService.findById(pat.getUserInfoId());
		return userInfo != null ? userInfo.getFname() + " " + userInfo.getLname() + " " + userInfo.getEmail() + ": " + pat.getDOB().toString() : "N/A";
    }
    
}

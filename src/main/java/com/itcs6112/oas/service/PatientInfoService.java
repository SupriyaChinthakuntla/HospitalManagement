package com.itcs6112.oas.service;

import com.itcs6112.oas.model.PatientInfo;
import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.repository.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientInfoService {

    @Autowired
    private PatientInfoRepository patientInfoRepository;
    
    @Autowired
    private UserInfoService userInfoService;
    
    private Iterable<PatientInfo> patients;
    
    public PatientInfoService(PatientInfoRepository patientInfoRepository) {
        this.patientInfoRepository = patientInfoRepository;
    }
    public void  fetchAllPatients(){
        this.patients = patientInfoRepository.findAll();
    }
    
    public Iterable<PatientInfo> getAllPatients(){
        return this.patients;
    }
    public PatientInfo findById(Integer ID){
        for(PatientInfo p : patients)
            if(p.getId().equals(ID))
                return p;
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
        return String.format("Patient: %s %s | DOB: %s | Email: %s", userInfo.getFname(), userInfo.getLname(), pat.getDOB(),userInfo.getEmail());
		// return userInfo != null ? userInfo.getFname() + " " + userInfo.getLname() + " " + userInfo.getEmail() + ": " + pat.getDOB().toString() : "N/A";
    }
    
}

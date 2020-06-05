package com.itcs6112.oas.service;

import com.itcs6112.oas.model.PatientInfo;
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
    
    public PatientInfoService(PatientInfoRepository patientInfoRepository) {
        this.patientInfoRepository = patientInfoRepository;
    }
    public Iterable<PatientInfo> getAllPatients(){
        return patientInfoRepository.findAll();
    }
    public Optional<PatientInfo> findById(Integer ID){
        return patientInfoRepository.findById(ID);
    }
    public boolean addNewPatient(Map<String,Object> requestBody){
        return createNewPatient(requestBody);
    }

    public void savePatient(PatientInfo patient) {
        patientInfoRepository.save(patient);
    }
    
    // returns true if a new patient is successfully created and added to the database
    private boolean createNewPatient(Map<String,Object>requestBody){
        // check to see if the request is properly formed, and create new patient 
        // Minimal error checking done
        if (parseNewPatientRequest(requestBody)){
            PatientInfo p = new PatientInfo();
            p.setDOB((Date) requestBody.get("patient_dob"));
            p.setId((Integer) requestBody.get("id"));
            savePatient(p);
            return true;
        }
        return false;
    }
    // helper function to determine if new patient request has all data fields
    private boolean parseNewPatientRequest(Map<String,Object>requestBody){
        String [] l = {"id","patient_dob"};
        for (String k : l)
            if(!requestBody.containsKey(k))
                return false;
        return true;
    }
}

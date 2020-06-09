package com.itcs6112.oas.service;

import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.repository.DoctorInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;


@Service
public class DoctorInfoService{

    @Autowired
    private DoctorInfoRepository doctorInfoRepository;
    
    public DoctorInfoService(DoctorInfoRepository doctorInfoRepository){
        this.doctorInfoRepository = doctorInfoRepository; 
    }
    
    public List<DoctorInfo> getAllDoctors(){
    	List<DoctorInfo> doctorList = new ArrayList<>();
        doctorInfoRepository.findAll().forEach(doctorList::add);
        return doctorList;
    }
   
    public Optional<DoctorInfo> findById(Integer ID){
        return doctorInfoRepository.findById(ID);
    }
    
    public List<DoctorInfo> findDoctorsBySpeciality(String speciality){
        return doctorInfoRepository.findBySpeciality(speciality);
    }
  
    public boolean addNewDoctor(Map<String,Object> requestBody){
        return createNewDoctor(requestBody);
    }

    public void saveDoctor(DoctorInfo doctor) {
        doctorInfoRepository.save(doctor);
    }
    
    // returns true if a new patient is successfully created and added to the database
    private boolean createNewDoctor(Map<String,Object>requestBody){
        // check to see if the request is properly formed, and create new patient 
        // Minimal error checking done
        if (checkNewDoctorRequest(requestBody)){
            DoctorInfo d = new DoctorInfo();
            d.setSpeciality((String) requestBody.get("speciality"));
        }
        return false;
    }
    // helper function to determine if new patient request has all data fields 
    private boolean checkNewDoctorRequest(Map<String,Object>requestBody){
        String [] l = {"speciality"};
        for (String k : l)
            if(!requestBody.containsKey(k))
                return false;
        return true;
    }
}

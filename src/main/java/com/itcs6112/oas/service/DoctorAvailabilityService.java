package com.itcs6112.oas.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.itcs6112.oas.model.AppointmentInfo;
import com.itcs6112.oas.model.DoctorAvailability;
import com.itcs6112.oas.model.DoctorInfo;
import com.itcs6112.oas.repository.DoctorAvailabilityRepository;

@Service
public class DoctorAvailabilityService {

	 @Autowired
	    private DoctorAvailabilityRepository doctorAvailabilityRepository;
	    
	    public DoctorAvailabilityService(DoctorAvailabilityRepository doctorAvailabilityRepository){
	        this.doctorAvailabilityRepository = doctorAvailabilityRepository; 
	    }
	    
	
	    public List<DoctorAvailability> getAllAvailabilities(){
	    	List<DoctorAvailability> doctorList = new ArrayList<>();
	    	doctorAvailabilityRepository.findAll().forEach(doctorList::add);
	        return doctorList;
	    }
	    
	    public List<DoctorAvailability> findByDoctorId(Integer doctorId) {
	    	return doctorAvailabilityRepository.findByDoctorId(doctorId);
	    }
	    
	      
}

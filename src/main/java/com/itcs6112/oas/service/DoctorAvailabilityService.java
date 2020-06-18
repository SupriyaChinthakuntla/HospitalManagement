package com.itcs6112.oas.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcs6112.oas.model.DoctorAvailability;
import com.itcs6112.oas.repository.DoctorAvailabilityRepository;

@Service
public class DoctorAvailabilityService {

    @Autowired
    private DoctorAvailabilityRepository doctorAvailabilityRepository;

    public List<DoctorAvailability> getAllAvailabilities() {
        System.out.println("\n\n\n");
        System.out.println("DoctorAvailabilityService");
        List<DoctorAvailability> doctorList = new ArrayList<>();
        doctorAvailabilityRepository.findAll().forEach(doctorList::add);
        return doctorList;
    }

    public List<DoctorAvailability> findByDoctorId(Integer doctorId) {
        return doctorAvailabilityRepository.findByDoctorId(doctorId);
    }

    public void saveAvailability(DoctorAvailability d){
        this.doctorAvailabilityRepository.save(d);
    }


}

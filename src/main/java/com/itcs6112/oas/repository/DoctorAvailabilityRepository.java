package com.itcs6112.oas.repository;


import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itcs6112.oas.model.DoctorAvailability;

public interface DoctorAvailabilityRepository extends CrudRepository<DoctorAvailability, Integer> {
	List<DoctorAvailability> findByDoctorId(Integer doctorId);
	      
}

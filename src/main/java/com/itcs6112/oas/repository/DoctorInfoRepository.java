package com.itcs6112.oas.repository;

import com.itcs6112.oas.model.DoctorInfo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("doctorInfoRepository")
public interface DoctorInfoRepository extends CrudRepository<DoctorInfo, Integer> {
	List<DoctorInfo> findBySpeciality(String speciality);
}
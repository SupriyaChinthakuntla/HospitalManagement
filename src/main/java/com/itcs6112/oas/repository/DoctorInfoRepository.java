package com.itcs6112.oas.repository;

import com.itcs6112.oas.model.DoctorInfo;

import org.springframework.data.repository.CrudRepository;

public interface DoctorInfoRepository extends CrudRepository<DoctorInfo, Integer> {
	Iterable<DoctorInfo> findBySpecialty(String specialty);
}
package com.itcs6112.oas.repository;

import com.itcs6112.oas.model.AppointmentInfo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AppointmentInfoRepository extends CrudRepository<AppointmentInfo, Integer> {
	List<AppointmentInfo> findByPatientId(Integer patientId);

}
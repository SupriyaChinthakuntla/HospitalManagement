package com.itcs6112.oas.repository;

import com.itcs6112.oas.model.PatientInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("patientInfoRepository")
public interface PatientInfoRepository extends CrudRepository<PatientInfo, Integer> {
}
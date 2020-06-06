package com.itcs6112.oas.repository;

import com.itcs6112.oas.model.AppointmentInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("appointmentInfoRepository")
public interface AppointmentInfoRepository extends CrudRepository<AppointmentInfo, Integer> {
}
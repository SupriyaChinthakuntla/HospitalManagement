package com.itcs6112.oas.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "doctor_availability")

public class DoctorAvailability {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer availability_id;

	@Column(name = "doctor_id")
	@NotEmpty
	private Integer doctorId;

	@Column(name = "available_time")
	@NotEmpty
	@DateTimeFormat
	private Timestamp doctorAvailableTime;

	public Integer getAvailability_id() {
		return availability_id;
	}

	public void setAvailability_id(Integer availability_id) {
		this.availability_id = availability_id;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Timestamp getDoctorAvailableTime() {
		return doctorAvailableTime;
	}

	public void setDoctorAvailableTime(Timestamp doctorAvailableTime) {
		this.doctorAvailableTime = doctorAvailableTime;
	}

	@Override
	public String toString() {
		return "DoctorAvailability [availability_id=" + availability_id + ", doctorId=" + doctorId
				+ ", doctorAvailableTime=" + doctorAvailableTime + "]";
	}

	
}

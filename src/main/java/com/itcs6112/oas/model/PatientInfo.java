package com.itcs6112.oas.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PatientInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date patient_dob;

	public Integer getId() {
		return id;
	}

	public Date getDOB() {
		return patient_dob;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDOB(Date date) {
		this.patient_dob = date;
	}

}
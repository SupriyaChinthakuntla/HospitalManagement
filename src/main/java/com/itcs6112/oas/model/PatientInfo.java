package com.itcs6112.oas.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_info")
public class PatientInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "patient_dob")
	private Date patientDOB;
	
	@Column(name = "user_info_id")
	private Integer userInfoId;

	//getters
	public Integer getId() {
		return id;
	}
	public Integer getUserInfoId(){
		return this.userInfoId;
	}
	public Date getDOB() {
		return patientDOB;
	}

	//setters
	public void setUserInfoId(Integer userInfoId){
		this.userInfoId = userInfoId;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDOB(Date date) {
		this.patientDOB = date;
	}

}
package com.itcs6112.oas.model;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.itcs6112.oas.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "doctor_info")
public class DoctorInfo{
	
	// @Autowired
	// private UserInfoService userInfoService;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "speciality")
	@NotEmpty
	private String specialty;

	@Column(name = "user_info_id")
	private Integer userInfoId;


	public Integer getUserInfoId(){
		return this.userInfoId;
	}
	public void setUserInfoId(final Integer id) {
		this.userInfoId = id;
	}

	public Integer getId() {
		return id;
	}

	public String getSpecialty() {
		return this.specialty;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setSpecialty(final String specialty) {
		this.specialty= specialty;
	}

}
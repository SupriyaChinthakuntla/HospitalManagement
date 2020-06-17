package com.itcs6112.oas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor_info")
public class DoctorInfo{
	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "speciality")
	private String specialty;

	@Column(name = "user_info_id")
	private Integer userInfoId;

	//getters
	public Integer getUserInfoId(){
		return this.userInfoId;
	}
	public Integer getId() {
		return id;
	}
	public String getSpeciality() {
		return this.specialty;
	}
	
	//setters
	public void setUserInfoId(final Integer id) {
		this.userInfoId = id;
	}
	public void setId(final Integer id) {

	@Override
	public String toString() {
		return "DoctorInfo [id=" + id + ", speciality=" + speciality + ", name=" + name + "]";
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setSpecialty(final String specialty) {
		this.specialty= specialty;
	}

}
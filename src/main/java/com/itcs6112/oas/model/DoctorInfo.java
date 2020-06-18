package com.itcs6112.oas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.itcs6112.oas.service.DoctorInfoService;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "doctor_info")
public class DoctorInfo{

	public DoctorInfo(){
		// this.name = doc.getDoctorName(this);
	}

	@Transient
	@Autowired
	private DoctorInfoService doc;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "speciality")
	private String specialty;

	@Column(name = "user_info_id")
	private Integer userInfoId;
	
	@Column(name = "name")
	private String name ;

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

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

	@Override
	public String toString() {
		return "DoctorInfo [id=" + id + ", speciality=" + specialty + ", name= UPDATE THIS";
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setSpecialty(final String specialty) {
		this.specialty= specialty;
	}

}
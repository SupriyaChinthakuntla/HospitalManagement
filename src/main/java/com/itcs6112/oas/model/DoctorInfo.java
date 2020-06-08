package com.itcs6112.oas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "doctor_info")
public class DoctorInfo{
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "specialty")
	@NotEmpty
	private String specialty;

	// @Column(name = "user_info")
	@OneToOne
	private UserInfo userInfo;

	public Integer getId() {
		return id;
	}

	public UserInfo getUserInfo(){
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo){
		this.userInfo = userInfo;
	}

	public String getSpecialty(){
		return this.specialty;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSpecialty(String specialty) {
		this.specialty= specialty;
	}

	public String getInfoString(){
		return this.userInfo.getFname() + " " + this.userInfo.getLname() + " " + this.userInfo.getEmail() + ": " + this.specialty;
	}

}
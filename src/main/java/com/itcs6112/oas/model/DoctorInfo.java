package com.itcs6112.oas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public Integer getId() {
		return id;
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

}
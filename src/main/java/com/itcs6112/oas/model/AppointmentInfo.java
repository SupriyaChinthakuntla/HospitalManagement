package com.itcs6112.oas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "appointment_info")
public class AppointmentInfo{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "patient_id")
	// @NotEmpty
	private Integer patientId;
	
	@Column(name = "doctor_id")
	// @NotEmpty	
	private Integer doctorId;
	
	@Column(name = "appt_notes")
	private String notes;
	
	@Column(name = "reason_for_visit")
	private String reasonForVisit;
	
	@Column(name = "date_start")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date startDate;
	
	@Column(name = "date_end")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date endDate;

	
	
	public AppointmentInfo() {}
	public AppointmentInfo(Integer patientId, Integer doctorId, String reasonForVisit, Date date_start) {
		this.patientId= patientId;
		this.doctorId= doctorId;
		this.reasonForVisit = reasonForVisit;
		this.startDate = date_start;
	}
	
	
	//setters
	public void setPatientId(Integer id){
		this.patientId = id;
	}
	public void setDoctorInfoId(Integer id){
		this.doctorId = id;
	}
	public void setReasonForVisit(String reason){
		this.reasonForVisit = reason;
	}
	public void setApptNotes(String notes){
		this.notes = notes;
	}
	public void setStartDate(Date date){
		this.startDate = date;
	}
	public void setEndDate(Date date){
		this.endDate= date;
	}
	
	//getters
	public Integer getDoctorId(){
		return this.doctorId;
	}
	public Integer getPatientId(){
		return this.patientId;
	}
	public String getReasonForVisit(){
		return this.reasonForVisit;
	}

	public String getNotes() {
		return this.notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getStartDate(){
		return this.startDate;

	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public Date getEndDate(){
		return this.endDate;
	}
}
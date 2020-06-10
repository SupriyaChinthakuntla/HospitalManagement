package com.itcs6112.oas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "appointment_info")
public class AppointmentInfo{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "patient_info_Id")
	@NotEmpty
	private Integer patientInfoId;
	
	// @Column(name = "doctor_id")
	@NotEmpty	
	private Integer doctorInfoId;
	
	@Column(name = "appt_notes")
	private String notes;
	
	@Column(name = "reason_for_visit")
	private String reasonForVisit;
	
	@Column(name = "date_start")
	@DateTimeFormat
	private Date date_start;
	
	@Column(name = "date_end")
	@DateTimeFormat
	private Date date_end;
	
	@Column(name = "cancelled")
	private Boolean cancelled;
	

	public void setPatientInfoId(Integer id){
		this.patientInfoId = id;
	}
	public void setDoctorInfoId(Integer id){
		this.doctorInfoId = id;
	}
	public void setReasonForVisit(String reason){
		this.reasonForVisit = reason;
	}
	public void setApptNotes(String notes){
		this.notes = notes;
	}
	public void setStartDate(Date date){
		this.date_start = date;
	}
	
	public void setEndDate(Date date){
		this.date_end= date;
	}
	
	public void setCancelled(Boolean cancelled){
		this.cancelled = cancelled;
	}
	
	public Integer getPatientInfoId(){
		return this.patientInfoId;
	}
	public Integer getDoctorInfoId(){
		return this.doctorInfoId;
	}
	public String getReasonForVisit(){
		return this.reasonForVisit;
	}
	public String getApptNotes(){
		return this.notes;
	}
	public Date getStartDate(){
		return this.date_start;
	}
	public Date getEndDate(){
		return this.date_end;
	}
	public Boolean getCancelled(){
		return this.cancelled;
	}
}
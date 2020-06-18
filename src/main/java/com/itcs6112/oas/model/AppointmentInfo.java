package com.itcs6112.oas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "appointment_info")
public class AppointmentInfo{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Override
	public String toString() {
		return "AppointmentInfo [id=" + id + ", patientId=" + patientId + ", doctorId=" + doctorId + ", notes=" + notes
				+ ", reasonForVisit=" + reasonForVisit + ", date_start=" + startDate + ", date_end=" + endDate 
				+ ", cancelled=" + cancelled + "]";
	}
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

	@Transient
	private String startDateString;
	
	@Transient
	private String endDateString;
	public void setStartDateString(String dateStartString){this.startDateString = dateStartString;}
	public void setEndDateString(String dateEndString){this.endDateString = dateEndString; }
	public String getStartDateString(){return this.startDateString;}
	public String getEndDateString(){return this.endDateString; }
	
	
	public AppointmentInfo() {}
	public AppointmentInfo(Integer patientId, Integer doctorId, String reasonForVisit, Date date_start) {
		this.patientId= patientId;
		this.doctorId= doctorId;
		this.reasonForVisit = reasonForVisit;
		this.startDate = date_start;
	}
	
	@Column(name = "cancelled")
	private Boolean cancelled;
	
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
	public void setCancelled(Boolean cancelled){
		this.cancelled = cancelled;
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
	// public void setReasonForVisit(String reasonForVisit) {
	// 	this.reasonForVisit = reasonForVisit;
	// }
	// public Boolean getCancelled(){
	// 	return this.cancelled;
	// }
}
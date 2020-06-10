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

	@Column(name = "patient_id")
	@NotEmpty
	private Integer patientInfoId;
	
	@Column(name = "doctor_id")
	@NotEmpty	
	private Integer doctorInfoId;
	
	@Column(name = "appt_notes")
	private String notes;
	
	@Column(name = "reason_for_visit")
	private String reasonForVisit;
	
	@Column(name = "date_start")
	@DateTimeFormat
	private Date dateStart;
	
	@Column(name = "date_end")
	@DateTimeFormat
	private Date dateEnd;
	
	@Column(name = "cancelled")
	private Boolean cancelled;
	
	//setters
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
		this.dateStart = date;
	}
	public void setEndDate(Date date){
		this.dateEnd= date;
	}
	public void setCancelled(Boolean cancelled){
		this.cancelled = cancelled;
	}
	
	//getters
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
		return this.dateStart;
	}
	public Date getEndDate(){
		return this.dateEnd;
	}
	public Boolean getCancelled(){
		return this.cancelled;
	}
}
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
	private Integer patientId;
	
	@Column(name = "doctor_id")
	@NotEmpty	
	private Integer doctorId;
	
	@Column(name = "appt_notes")
	@NotEmpty	
	private String notes;
	
	@Column(name = "reason_for_visit")
	@NotEmpty	
	private String reasonForVisit;
	
	@Column(name = "date_start")
	@NotEmpty	
	@DateTimeFormat
	private Date date_start;
	
	@Column(name = "date_end")
	@NotEmpty	
	@DateTimeFormat
	private Date date_end;
	
	@Column(name = "cancelled")
	@NotEmpty	
	private Boolean cancelled;
	

	public void setReasonForVisit(String reason){
		this.reasonForVisit = reason;
	}
	public void setPatientId(Integer id){
		this.patientId = id;
	}
	public void setDoctorId(Integer id){
		this.doctorId = id;
	}
	public void setApptNotes(String notes){
		this.notes = notes;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public void setCancelled(Boolean cancelled){
		this.cancelled = cancelled;
	}
	
	public String getReasonForVisit(String reason){
		return this.reasonForVisit;
	}
	public Integer getPatientId(Integer id){
		return this.patientId;
	}
	public Integer getDoctorId(Integer id){
		return this.doctorId;
	}
	public String getApptNotes(String notes){
		return this.notes;
	}
	public Date getDate(Date date){
		return this.date;
	}
	public Boolean getCancelled(Boolean cancelled){
		return this.cancelled;
	}



}
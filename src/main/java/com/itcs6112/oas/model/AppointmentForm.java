package com.itcs6112.oas.model;

import java.sql.Time;
import java.sql.Timestamp;

public class AppointmentForm {

    private String reasonForVisit;
    private Integer doctorId;
    private String doctorAvailableTime;

    public AppointmentForm(String reasonForVisit, Integer doctorId, String doctorAvailableTime) {
        this.reasonForVisit = reasonForVisit;
        this.doctorId = doctorId;
        this.doctorAvailableTime = doctorAvailableTime;
    }

    public AppointmentForm() {
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorAvailableTime() {
        return doctorAvailableTime;
    }

    public void setDoctorAvailableTime(String doctorAvailableTime) {
        this.doctorAvailableTime = doctorAvailableTime;
    }
}

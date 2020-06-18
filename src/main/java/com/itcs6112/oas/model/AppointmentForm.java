package com.itcs6112.oas.model;


public class AppointmentForm {

    private String reasonForVisit;
    private Integer doctorId;
    private String doctorAvailableTime;
    private String spec;

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
    public String getSpec(){
        return this.spec;
    }
    public void setSpec(String spec){
        this.spec = spec;
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

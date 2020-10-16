package com.haulmont.testtask.backend.entity;

import java.util.Date;

public class Recipe{

    private Long id=null;
    private String description;
    private Patient patient;
    private Doctor doctor;
    private Date creationDate;
    private Date expirationDate;
    private String priority;

    public Recipe(){}

    public Recipe(Long id, String description, Patient patient, Doctor doctor,
                  Date creationDate, Date expirationDate, String priority){
        this.id=id;
        this.description=description;
        this.patient=patient;
        this.doctor=doctor;
        this.creationDate=creationDate;
        this.expirationDate=expirationDate;
        this.priority=priority;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}

package com.haulmont.testtask.backend.entity;

public class Doctor {

    private Long id=null;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String specialization;

    public Doctor(){}

    public Doctor(Long id, String firstName, String lastName, String patronymic,String specialization){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.patronymic=patronymic;
        this.specialization=specialization;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}

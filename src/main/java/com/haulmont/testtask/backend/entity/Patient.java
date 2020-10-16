package com.haulmont.testtask.backend.entity;

public class Patient {

    private Long id=null;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;

    public Patient(){}

    public Patient(Long id, String firstName, String lastName, String patronymic, String phone){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.patronymic=patronymic;
        this.phone = phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

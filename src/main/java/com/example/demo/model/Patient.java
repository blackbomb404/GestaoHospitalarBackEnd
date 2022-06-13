package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Patient{
    private Integer id;
    private String firstname;
    private String lastname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private User user;

    public Patient() { }

    public Patient(String firstname, String lastname, LocalDate birthdate, User user) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.user = user;
    }

    public int getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public User getUserCredentials() { return user; }
    public void setUserCredentials(User user) { this.user = user; }
}

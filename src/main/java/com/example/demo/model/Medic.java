package com.example.demo.model;

import lombok.Data;

@Data
public class Medic {
    private Integer id;
    private String firstname;
    private String lastname;
    private Specialty specialty;
}

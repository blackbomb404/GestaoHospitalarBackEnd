package com.example.demo.dto;

import com.example.demo.model.Medic;
import com.example.demo.model.Specialty;
import lombok.Data;

@Data
public class MedicDto {
    private String firstname;
    private String lastname;
    private Integer specialtyId;

    public Medic toModel(){
        var medic = new Medic();
        medic.setFirstname(firstname);
        medic.setLastname(lastname);

        var specialty = new Specialty();
        specialty.setId(specialtyId);

        medic.setSpecialty(specialty);
        return medic;
    }
}

package com.example.demo.dto;

import com.example.demo.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private String firstname;
    private String lastname;
    private String birthdate;

    public Patient toModel(){
        int indexOfT = this.birthdate.indexOf("T");
        String birthdate = this.birthdate.substring(0, indexOfT != -1 ? indexOfT : this.birthdate.length());
        return new Patient(null, this.firstname, this.lastname, LocalDate.parse(birthdate));
    }
}

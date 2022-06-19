package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient{
    private Integer id;
    private String firstname;
    private String lastname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
}

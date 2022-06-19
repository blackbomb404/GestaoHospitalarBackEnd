package com.example.demo.service;

import com.example.demo.dto.PatientDto;
import com.example.demo.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PatientService extends CrudService<PatientDto, Patient, Integer>{
    List<Patient> getByName(String name);
}

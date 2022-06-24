package com.example.demo.controller;

import com.example.demo.dto.PatientDto;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public int add(@RequestBody PatientDto patientDto){
        return patientService.add(patientDto);
    }

    @GetMapping
    public List<Patient> getAll(@RequestParam(required = false) String firstname){
        if(firstname == null)
            return patientService.getAll();
        return patientService.getByFirstname(firstname);
    }

    @GetMapping("{id}")
    public Patient get(@PathVariable Integer id){
        Optional<Patient> foundPatient = patientService.get(id);
        return foundPatient.orElse(null);
    }

    @PutMapping(value = "{id}")
    public int update(@RequestBody PatientDto patientDto, @PathVariable Integer id){
        return patientService.update(patientDto, id);
    }

    @DeleteMapping(value = "{id}")
    public int delete(@PathVariable Integer id){
        return patientService.delete(id);
    }
}

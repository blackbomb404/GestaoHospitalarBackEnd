package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
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
    public String add(@RequestBody Patient patient){
        return patientService.add(patient) == 1 ? "Successfully created!" : "Something went wrong...";
    }

    @GetMapping
    public List<Patient> getAll(){
        return patientService.getAll();
    }

    @GetMapping("{id}")
    public Patient getById(@PathVariable Integer id){
        Optional<Patient> foundPatient = patientService.get(id);
        return foundPatient.orElse(new Patient());
    }

    @PutMapping("{id}")
    public String update(@RequestBody Patient patient, @PathVariable Integer id){
        return patientService.update(patient, id) == 1 ? "Successfully updated!" : "Something went wrong...";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id){
        return patientService.delete(id) == 1 ? "Successfully deleted!" : "Something went wrong...";
    }
}

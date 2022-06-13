package com.example.demo.service;

import com.example.demo.dao.PatientDao;
import com.example.demo.model.Patient;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{
    private final PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public int add(Patient patient) {
        return patientDao.create(patient);
    }

    @Override
    public List<Patient> getAll() {
        return patientDao.read();
    }

    @Override
    public Optional<Patient> get(Integer id) {
        return patientDao.read(id);
    }

    @Override
    public int update(Patient patient, Integer id) {
        return patientDao.update(patient, id);
    }

    @Override
    public int delete(Integer id) {
        return patientDao.delete(id);
    }
}

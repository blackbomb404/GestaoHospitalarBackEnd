package com.example.demo.dao;

import com.example.demo.model.Patient;
import com.example.demo.model.User;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class PatientDao implements GenericDao<Patient, Patient, Integer>{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Patient> rowMapper = (rs, rowNumber) -> {
        Patient patient = new Patient();

        patient.setId(rs.getInt("id"));
        patient.setFirstname(rs.getString("firstname"));
        patient.setLastname(rs.getString("lastname"));
        patient.setBirthdate(rs.getObject("birthdate", LocalDate.class));

        return patient;
    };

    @Override
    public int create(Patient patient) {
        String sql = "CALL usp_patient_insert(?,?,?)";
        return jdbcTemplate.update(sql, patient.getFirstname(), patient.getLastname(), patient.getBirthdate());
    }

    @Override
    public Optional<Patient> read(Integer id) {
        String sql = "CALL usp_patient_selectById(?)";
        Patient patient = null;
        try {
            patient = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex){
            System.out.printf("Could not find Patient with id of '%s'%n", id);
        }
        return Optional.ofNullable(patient);
    }

    @Override
    public List<Patient> read() {
        String sql = "CALL usp_patient_selectAll()";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Patient> getByFirstname(String firstname){
        String sql = "CALL usp_patient_selectByFirstname(?)";
        return jdbcTemplate.query(sql, rowMapper, firstname);
    }

    @Override
    public int update(Patient patient, Integer id) {
        String sql = "CALL usp_patient_update(?,?,?,?)";
        return jdbcTemplate.update(sql, patient.getFirstname(), patient.getLastname(), patient.getBirthdate(), id);
    }

    @Override
    public int delete(Integer id) {
        String sql = "CALL usp_patient_deleteById(?)";
        return jdbcTemplate.update(sql, id);
    }
}

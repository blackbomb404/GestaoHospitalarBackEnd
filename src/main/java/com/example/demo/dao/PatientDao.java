package com.example.demo.dao;

import com.example.demo.model.Patient;
import com.example.demo.model.User;
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
        patient.setFirstname(rs.getString("primeiro_nome"));
        patient.setLastname(rs.getString("ultimo_nome"));
        patient.setBirthdate(rs.getObject("data_nascimento", LocalDate.class));

        return patient;
    };

    @Override
    public int create(Patient patient) {
        String sql = "INSERT INTO paciente(primeiro_nome, ultimo_nome, data_nascimento) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, patient.getFirstname(), patient.getLastname(), patient.getBirthdate());
    }

    @Override
    public Optional<Patient> read(Integer id) {
        String sql = "SELECT id, primeiro_nome, ultimo_nome, data_nascimento FROM paciente WHERE id = ?";
        Patient patient = null;
        try {
            patient = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex){
            System.out.printf("Could not find Patient with id of '%s'%n", id);
        }
        User user = null;
        return Optional.ofNullable(patient);
    }

    @Override
    public List<Patient> read() {
        String sql = "SELECT id, primeiro_nome, ultimo_nome, data_nascimento FROM paciente;";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public int update(Patient patient, Integer id) {
        String sql = "UPDATE paciente SET primeiro_nome=?, ultimo_nome=?, data_nascimento=? WHERE id = ?";
        return jdbcTemplate.update(sql, patient.getFirstname(), patient.getLastname(), patient.getBirthdate(), id);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM paciente WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

package com.example.demo.dao;

import com.example.demo.dao.GenericDao;
import com.example.demo.model.Medic;
import com.example.demo.model.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MedicDao implements GenericDao<Medic, Medic, Integer> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Medic> rowMapper = (rs, rowNum) -> {
        Medic medic = new Medic();
        medic.setId(rs.getInt("id"));
        medic.setFirstname(rs.getString("firstname"));
        medic.setLastname(rs.getString("lastname"));

        Specialty specialty = new Specialty();
        specialty.setId(rs.getInt("id_spec"));
        specialty.setName(rs.getString("spec_name"));

        medic.setSpecialty(specialty);
        return medic;
    };

    @Override
    public int create(Medic medic) {
        String sql = "CALL usp_medic_insert(?,?,?)";
        return jdbcTemplate.update(sql, medic.getFirstname(), medic.getLastname(), medic.getSpecialty().getId());
    }

    @Override
    public Optional<Medic> read(Integer id){
        String sql = "CALL usp_medic_selectById(?);";
        Medic medic = null;
        try{
            medic = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex){
            System.out.printf("Could not find medic with id of '%s'%n", id);
        }
        return Optional.ofNullable(medic);
    }

    @Override
    public List<Medic> read() {
        String sql = "CALL usp_medic_selectAll()";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Medic> getByFirstname(String name){
        String sql = "CALL usp_medic_selectByFirstname(?);";
        return jdbcTemplate.query(sql, rowMapper, name);
    }

    @Override
    public int update(Medic medic, Integer id) {
        String sql = "CALL usp_medic_update(?,?,?,?)";
        return jdbcTemplate.update(sql, medic.getFirstname(), medic.getLastname(), medic.getSpecialty().getId(), id);
    }

    @Override
    public int delete(Integer id) {
        String sql = "CALL usp_medic_deleteById(?)";
        return jdbcTemplate.update(sql, id);
    }
}

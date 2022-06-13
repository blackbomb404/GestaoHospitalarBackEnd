package com.example.demo.dao;

import com.example.demo.model.Specialty;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SpecialtyDao implements GenericDao<Specialty, Specialty, Integer> {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Specialty>  rowMapper = (rs, rowNum) -> {
        Specialty specialty = new Specialty();

        specialty.setId(rs.getInt("id"));
        specialty.setName(rs.getString("nome"));

        return specialty;
    };

    public SpecialtyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Specialty specialty) {
        return 0;
    }

    @Override
    public Optional<Specialty> read(Integer id) {
        String sql = "SELECT id, nome FROM especialidade WHERE id = ?";
        Specialty specialty = null;
        try{
            specialty = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex){
            System.out.printf("Could not find Specialty with id of '%s'%n", id);
        }
        return Optional.ofNullable(specialty);
    }

    @Override
    public List<Specialty> read() {
        String sql = "SELECT id, nome FROM especialidade";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public int update(Specialty specialty, Integer integer) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }
}

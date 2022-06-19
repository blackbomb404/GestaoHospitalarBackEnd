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
        String sql = "INSERT INTO especialidade(nome) VALUES (?)";
        return jdbcTemplate.update(sql, specialty.getName());
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

    public List<Specialty> getByName(String name){
        String sql = "SELECT id, nome FROM especialidade WHERE nome LIKE ?";
        return jdbcTemplate.query(sql, rowMapper, String.format("%2$s%1$s%2$s", name, "%"));
    }

    @Override
    public int update(Specialty specialty, Integer id) {
        String sql = "UPDATE especialidade SET nome = ? WHERE id = ?";
        return jdbcTemplate.update(sql, specialty.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM especialidade WHERE especialidade.id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

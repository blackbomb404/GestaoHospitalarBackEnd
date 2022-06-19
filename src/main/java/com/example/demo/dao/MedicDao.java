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
        medic.setFirstname(rs.getString("primeiro_nome"));
        medic.setLastname(rs.getString("ultimo_nome"));

        Specialty specialty = new Specialty();
        specialty.setId(rs.getInt("id_esp"));
        specialty.setName(rs.getString("nome_esp"));

        medic.setSpecialty(specialty);
        return medic;
    };

    @Override
    public int create(Medic medic) {
        String sql = "INSERT INTO medico(primeiro_nome, ultimo_nome, id_especialidade) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, medic.getFirstname(), medic.getLastname(), medic.getSpecialty().getId());
    }

    @Override
    public Optional<Medic> read(Integer id){
        String sql = "SELECT medico.id, primeiro_nome, ultimo_nome, esp.id AS id_esp, esp.nome AS nome_esp FROM medico\n" +
                "INNER JOIN especialidade AS esp ON medico.id_especialidade = esp.id WHERE medico.id = ?";
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
        String sql = "SELECT medico.id, primeiro_nome, ultimo_nome, esp.id AS id_esp, esp.nome AS nome_esp FROM medico\n" +
                "INNER JOIN especialidade AS esp ON medico.id_especialidade = esp.id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Medic> getByName(String name){
        String sql = "SELECT medico.id, primeiro_nome, ultimo_nome, esp.id AS id_esp, esp.nome AS nome_esp FROM medico\n" +
                "INNER JOIN especialidade AS esp ON medico.id_especialidade = esp.id WHERE primeiro_nome LIKE ?";
        return jdbcTemplate.query(sql, rowMapper, String.format("%2$s%1$s%2$s", name, "%"));
    }

    @Override
    public int update(Medic medic, Integer id) {
        String sql = "UPDATE medico SET primeiro_nome = ?, ultimo_nome = ?, id_especialidade = ? WHERE id = ?";
        return jdbcTemplate.update(sql, medic.getFirstname(), medic.getLastname(), medic.getSpecialty().getId(), id);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM medico WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

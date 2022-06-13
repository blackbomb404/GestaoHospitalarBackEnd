package com.example.demo.dao;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao implements GenericDao<UserDto, User, String>{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Integer> rowMapper2 = (rs2, rowNum2) -> rs2.getInt("id_authority");
    private final RowMapper<String> rowMapper3 = (rs3, rowNum3) -> rs3.getString("authority");

    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname(rs.getString("lastname"));
        user.setEmail(rs.getString("email"));
        user.setSalary(rs.getDouble("salary"));

        String sql = "SELECT id_authority FROM user_authority WHERE username = ?";
        List<Integer> authoritiesIds = jdbcTemplate.query(sql, rowMapper2, user.getUsername());

        List<SimpleGrantedAuthority> grantedAuthorities = authoritiesIds.stream()
                .map(id -> {
                    String localSql = "SELECT authority FROM authority WHERE id = ?";
                    String authority = null;
                    try{
                        authority = jdbcTemplate.queryForObject(localSql, rowMapper3, id);
                    }catch (DataAccessException ex){
                        System.out.println("Error occurred...");
                    }
                    return authority;
                })
                .map(SimpleGrantedAuthority::new)
                .toList();
        user.setGrantedAuthorities(grantedAuthorities);

        return user;
    };

    @Override
    public int create(UserDto user) {
        int userAffectedRows = jdbcTemplate.update("INSERT INTO user VALUES(?,?,?,?,?,?)",
                user.getUsername(), user.getPassword(), user.getFirstname(),
                user.getLastname(), user.getEmail(), user.getSalary());

        List<String> sqlSyntax = new ArrayList<>();
        List<Object> data = new ArrayList<>();
        user.getAuthoritiesIds().forEach(id -> {
            sqlSyntax.add("(?,?)");
            data.add(user.getUsername());
            data.add(id);
        });
        String sql = "INSERT INTO user_authority VALUES" + String.join(", ", sqlSyntax);
        int authorityAffectedRows = jdbcTemplate.update(sql, data.toArray());
        return 0;
    }

    @Override
    public Optional<User> read(String username) {
        User user = null;
        String sql = "SELECT username, password, firstname, lastname, email, salary FROM user WHERE username = ?";
        try{
            user = jdbcTemplate.queryForObject(sql, rowMapper, username);
        } catch (DataAccessException ex){
            System.out.printf("Could not find User with id of '%s'%n", username);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> read() {
        String sql = "SELECT username, password, firstname, lastname, email, salary FROM user";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public int update(UserDto userDto, String s) {
        return 0;
    }

    @Override
    public int delete(String s) {
        return 0;
    }
}

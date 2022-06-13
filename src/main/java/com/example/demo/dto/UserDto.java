package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Value
public class UserDto {
    String firstname;
    String lastname;
    String email;
    Double salary;
    String username;
    String password;
    List<Integer> authoritiesIds;
}

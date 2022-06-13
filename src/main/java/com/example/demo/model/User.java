package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String firstname;
    private String lastname;
    private String email;
    private Double salary;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
}

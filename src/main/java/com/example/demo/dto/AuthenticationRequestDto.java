package com.example.demo.dto;

import lombok.Value;

@Value
public class AuthenticationRequestDto {
    String username;
    String password;
}

package com.example.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({SQLSyntaxErrorException.class})
    public ResponseEntity<Map<String, String>> permissionDenied(){
        var map = new HashMap<String, String>();
        String message = "Você não tem permissão para realizar esta operação.";
        map.put("message", message);
        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }
}

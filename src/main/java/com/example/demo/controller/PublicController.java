package com.example.demo.controller;

import com.example.demo.dto.AuthenticationRequestDto;
import com.example.demo.dto.AuthenticationResponseDto;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("public")
    public String home(){
        return "<h2>Hello Public</h2>";
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> authentication(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        String username = authenticationRequestDto.getUsername();
        String password = authenticationRequestDto.getPassword();

        try{
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String jwt = JwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponseDto(jwt), HttpStatus.OK);

        }catch (BadCredentialsException ex){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}

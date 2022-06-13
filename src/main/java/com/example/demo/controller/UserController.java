package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static java.lang.String.format;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String home(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return format("<h2>Successfull Login!<br>Be welcome, %s .</h2>", userDetails.getUsername());
    }

    @PostMapping
    public String add(@RequestBody UserDto userDto){
        return userService.add(userDto) == 1 ? "User successfuly created!" : "Something went wrong...";
    }

}

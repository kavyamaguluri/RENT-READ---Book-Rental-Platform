package com.crio.rent_read.controllers;

import jakarta.validation.Valid;
import com.crio.rent_read.dto.LoginRequest;
import com.crio.rent_read.dto.SignupRequest;
import com.crio.rent_read.dto.UserResponse;
import com.crio.rent_read.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        UserResponse userResponse = userService.register(signupRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        UserResponse userResponse = userService.login(loginRequest);
        return ResponseEntity.ok(userResponse);
    }
}
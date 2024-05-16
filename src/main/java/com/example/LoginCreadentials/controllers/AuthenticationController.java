package com.example.LoginCreadentials.controllers;

import com.example.LoginCreadentials.dtos.LoginUserDto;
import com.example.LoginCreadentials.dtos.RegisterUserDto;
import com.example.LoginCreadentials.entities.UserEntity;
import com.example.LoginCreadentials.responses.LoginResponse;
import com.example.LoginCreadentials.service.AuthenticationService;
import com.example.LoginCreadentials.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/signup")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterUserDto registerUserDto) {
        UserEntity registeredUserEntity = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUserEntity);
    }

    @GetMapping("/authState")
    @PreAuthorize("isAuthenticated()")
    public String isAuthenticated(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return "logedin";
        }
        return "loggedout";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserEntity authenticatedUserEntity = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUserEntity);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return ResponseEntity.ok(true);
    }
}
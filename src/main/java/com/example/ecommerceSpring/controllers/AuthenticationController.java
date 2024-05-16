package com.example.ecommerceSpring.controllers;

import com.example.ecommerceSpring.dtos.LoginUserDto;
import com.example.ecommerceSpring.dtos.RegisterUserDto;
import com.example.ecommerceSpring.entities.UserEntity;
import com.example.ecommerceSpring.responses.ApiResponse;
import com.example.ecommerceSpring.responses.LoginResponse;
import com.example.ecommerceSpring.service.AuthenticationService;
import com.example.ecommerceSpring.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<ApiResponse<RegisterUserDto>> register(@RequestBody RegisterUserDto registerUserDto) {
        RegisterUserDto registeredUserEntity = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(new ApiResponse(true, null, registeredUserEntity));
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
    public ResponseEntity<ApiResponse<LoginResponse>> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserEntity authenticatedUserEntity = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUserEntity);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(new ApiResponse<>(true, null, loginResponse));
    }
}
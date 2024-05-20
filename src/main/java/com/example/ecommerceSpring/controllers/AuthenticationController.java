package com.example.ecommerceSpring.controllers;

import com.example.ecommerceSpring.dtos.users.LoginUserDto;
import com.example.ecommerceSpring.dtos.users.RegisterUserDto;
import com.example.ecommerceSpring.entities.UserEntity;
import com.example.ecommerceSpring.exception.CustomException;
import com.example.ecommerceSpring.responses.ApiResponse;
import com.example.ecommerceSpring.responses.LoginResponse;
import com.example.ecommerceSpring.service.AuthenticationService;
import com.example.ecommerceSpring.service.JwtService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.util.StringUtil;
import org.springframework.http.HttpStatus;
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

        if (StringUtils.isEmpty(registerUserDto.getEmail()) || StringUtils.isEmpty(registerUserDto.getPassword()) || StringUtils.isEmpty(registerUserDto.getFullName())) {
            throw new CustomException("No empty fields are acepted", HttpStatus.BAD_REQUEST);
        }


        if (AuthenticationService.isValidPassword(registerUserDto.getPassword())) {
            throw new CustomException("Invalid password format", HttpStatus.BAD_REQUEST);
        }

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
        if (StringUtils.isEmpty(loginUserDto.getEmail()) || StringUtils.isEmpty(loginUserDto.getPassword())) {
            throw new CustomException("No empty fields are acepted", HttpStatus.BAD_REQUEST);
        }

        UserEntity authenticatedUserEntity = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUserEntity);

        LoginResponse loginResponse = new LoginResponse(jwtToken, authenticatedUserEntity.getFullName(), jwtService.getExpirationTime());

        return ResponseEntity.ok(new ApiResponse<>(true, null, loginResponse));
    }
}
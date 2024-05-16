package com.example.ecommerceSpring.controllers;

import com.example.ecommerceSpring.dtos.RegisterUserDto;
import com.example.ecommerceSpring.dtos.UserDto;
import com.example.ecommerceSpring.entities.UserEntity;
import com.example.ecommerceSpring.responses.ApiResponse;
import com.example.ecommerceSpring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@RestController
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<UserDto>> createAdministrator(@RequestBody RegisterUserDto registerUserDto) {
        UserDto createdAdmin = userService.createAdministrator(registerUserDto);
        return ResponseEntity.ok(new ApiResponse<>(true, null, createdAdmin));
    }
}

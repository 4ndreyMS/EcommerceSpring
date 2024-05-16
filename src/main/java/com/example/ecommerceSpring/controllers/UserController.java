package com.example.ecommerceSpring.controllers;

import com.example.ecommerceSpring.dtos.users.UserDto;
import com.example.ecommerceSpring.responses.ApiResponse;
import com.example.ecommerceSpring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<UserDto>> authenticatedUser() {
        return ResponseEntity.ok(new ApiResponse<>(true, null, userService.authenticatedUser()));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<List<UserDto>>> allUsers() {

        List<UserDto> userModel = userService.allUsers();

        return ResponseEntity.ok(new ApiResponse<>(true, null, userModel));
    }


}

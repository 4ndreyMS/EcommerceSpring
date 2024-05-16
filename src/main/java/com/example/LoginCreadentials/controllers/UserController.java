package com.example.LoginCreadentials.controllers;

import com.example.LoginCreadentials.entities.UserEntity;
import com.example.LoginCreadentials.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<UserEntity> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity currentUserEntity = (UserEntity) authentication.getPrincipal();

        return ResponseEntity.ok(currentUserEntity);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<UserEntity>> allUsers() {

        List<UserEntity> userEntities = userService.allUsers();

        return ResponseEntity.ok(userEntities);
    }
}

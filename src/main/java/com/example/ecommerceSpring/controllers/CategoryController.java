package com.example.ecommerceSpring.controllers;

import com.example.ecommerceSpring.dtos.CategoryDto;
import com.example.ecommerceSpring.dtos.users.RegisterUserDto;
import com.example.ecommerceSpring.enums.CategoryEnum;
import com.example.ecommerceSpring.responses.ApiResponse;
import com.example.ecommerceSpring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(true, null, categoryService.getAllCategories()));
    }
}

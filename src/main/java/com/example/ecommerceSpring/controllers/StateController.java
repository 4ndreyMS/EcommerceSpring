package com.example.ecommerceSpring.controllers;

import com.example.ecommerceSpring.dtos.Cart.CartItemDto;
import com.example.ecommerceSpring.dtos.StateDto;
import com.example.ecommerceSpring.dtos.users.InsertDto;
import com.example.ecommerceSpring.responses.ApiResponse;
import com.example.ecommerceSpring.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/state")
public class StateController {
    @Autowired
    private StateService stateService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Boolean>> create(@RequestBody List<StateDto> insertDto) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, stateService.create(insertDto)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<StateDto>>> getCartItems() {
        return ResponseEntity.ok(new ApiResponse<>(true, null, stateService.getAllStates()));
    }

}

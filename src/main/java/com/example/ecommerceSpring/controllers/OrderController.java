package com.example.ecommerceSpring.controllers;

import com.example.ecommerceSpring.dtos.Cart.CartItemDto;
import com.example.ecommerceSpring.dtos.Cart.OrderDto;
import com.example.ecommerceSpring.dtos.users.InsertDto;
import com.example.ecommerceSpring.entities.OrderEntity;
import com.example.ecommerceSpring.responses.ApiResponse;
import com.example.ecommerceSpring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Boolean>> create(@RequestBody OrderDto orderInfo) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, orderService.createOrder(orderInfo)));
    }

    @GetMapping("/allUserOrders")
    public ResponseEntity<ApiResponse<List<OrderEntity>>> getAllUserOrders() {
        return ResponseEntity.ok(new ApiResponse<>(true, null, orderService.getCurrentUserOrdersInfo()));
    }

}

package com.example.ecommerceSpring.controllers;

import com.example.ecommerceSpring.dtos.Orders.OrderDto;
import com.example.ecommerceSpring.dtos.Orders.OrderWithUserDto;
import com.example.ecommerceSpring.responses.ApiResponse;
import com.example.ecommerceSpring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<ApiResponse<List<OrderDto>>> getAllUserOrders() {
        return ResponseEntity.ok(new ApiResponse<>(true, null, orderService.getCurrentUserOrdersInfo()));
    }

    @GetMapping("/allOrder")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<List<OrderDto>>> getAllOrder() {
        return ResponseEntity.ok(new ApiResponse<>(true, null, orderService.getAllOrdersInfo()));

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<OrderWithUserDto>> findById(@PathVariable long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, orderService.findById(id)));
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<Boolean>> updateStatus(@RequestBody OrderDto orderInfo) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, orderService.updateOrderStatus(orderInfo)));
    }
}

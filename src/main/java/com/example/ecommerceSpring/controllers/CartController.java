package com.example.ecommerceSpring.controllers;

import com.example.ecommerceSpring.dtos.Cart.CartItemDto;
import com.example.ecommerceSpring.dtos.ProductDto;
import com.example.ecommerceSpring.dtos.users.InsertDto;
import com.example.ecommerceSpring.entities.CartEntity;
import com.example.ecommerceSpring.exception.CustomException;
import com.example.ecommerceSpring.repositories.CartRepository;
import com.example.ecommerceSpring.responses.ApiResponse;
import com.example.ecommerceSpring.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {


    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Boolean>> create(@RequestBody InsertDto insertDto) {
        //validate if the user with the jwt exit
        // validate if the product exist
        return ResponseEntity.ok(new ApiResponse<>(true, null, cartService.addToCart(insertDto)));
    }

    @GetMapping("/getCartItems")
    public ResponseEntity<ApiResponse<List<CartItemDto>>> getCartItems() {
        return ResponseEntity.ok(new ApiResponse<>(true, null, cartService.getCartItems()));
    }

    @Transactional
    @DeleteMapping("/deleteCartItem/{productId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteCartItem(@PathVariable long productId) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, cartService.deleteCartItem(productId)));

    }

    @Transactional
    @DeleteMapping("/cleanCart")
    public ResponseEntity<ApiResponse<Boolean>> cleanCart() {
        return ResponseEntity.ok(new ApiResponse<>(true, null, cartService.clearCart()));
    }
}

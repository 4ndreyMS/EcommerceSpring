package com.example.ecommerceSpring.controllers;

import com.example.ecommerceSpring.dtos.ProductDto;
import com.example.ecommerceSpring.exception.CustomException;
import com.example.ecommerceSpring.responses.ApiResponse;
import com.example.ecommerceSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProductDto>> create(@RequestBody ProductDto newProduct) {
        //validate if the product al ready exist
        if (productService.exist(newProduct)) {
            throw new CustomException("Product already exist", HttpStatus.CONFLICT);
        }
        System.out.println(newProduct);
        return ResponseEntity.ok(new ApiResponse<>(true, null, productService.save(newProduct)));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<ProductDto>> update(@RequestBody ProductDto newProduct) {
        //validate if the product al ready exist
        if (!productService.exist(newProduct)) {
            throw new CustomException("Product not found", HttpStatus.BAD_REQUEST);
        }
        System.out.println(newProduct);
        return ResponseEntity.ok(new ApiResponse<>(true, null, productService.save(newProduct)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(true, null, productService.findAll()));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> findById(@PathVariable long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, productService.findById(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, productService.deleteById(id)));
    }

    @GetMapping("/getAllFeatured")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllFeatured() {
        return ResponseEntity.ok(new ApiResponse<>(true, null, productService.getAllFeatured()));
    }

    @DeleteMapping("/softDelete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> softDelete(@PathVariable long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, null, productService.softDelete(new ProductDto(id, true))));
    }
}

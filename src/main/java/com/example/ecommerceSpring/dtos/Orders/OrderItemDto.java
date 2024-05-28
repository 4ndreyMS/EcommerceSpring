package com.example.ecommerceSpring.dtos.Orders;

import com.example.ecommerceSpring.dtos.ProductDto;
import com.example.ecommerceSpring.dtos.users.UserDto;

import java.security.PrivateKey;

public class OrderItemDto {
    private ProductDto product;
    private int quantity;
    private double price;
    private UserDto user;

    public OrderItemDto() {
    }

    public OrderItemDto(ProductDto product, int quantity, double price, UserDto user) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto userDto) {
        this.user = userDto;
    }
}

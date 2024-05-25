package com.example.ecommerceSpring.dtos.Cart;

import com.example.ecommerceSpring.dtos.ProductDto;

public class CartItemDto {
    private ProductDto product;
    private int quantity;
    private double price;

    public CartItemDto() {
    }

    public CartItemDto(ProductDto product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto productDto) {
        this.product = productDto;
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
}

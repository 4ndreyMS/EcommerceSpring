package com.example.ecommerceSpring.entities;

import com.example.ecommerceSpring.dtos.ProductDto;

public class CartItemEntity {
    private ProductDto product;
    private int quantity;

    public CartItemEntity() {
    }

    public CartItemEntity(ProductDto product, int quantity) {
        this.product = product;
        this.quantity = quantity;
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
}

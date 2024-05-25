package com.example.ecommerceSpring.dtos.users;

import com.example.ecommerceSpring.dtos.ProductDto;
import com.example.ecommerceSpring.entities.UserEntity;

public class InsertDto {
    private ProductDto product;
    private int quantity;

    public InsertDto() {
    }

    public InsertDto(UserDto user, ProductDto product) {
        this.product = product;
    }

    public InsertDto(UserDto user, ProductDto product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

}

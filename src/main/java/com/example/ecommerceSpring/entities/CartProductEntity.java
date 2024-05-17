package com.example.ecommerceSpring.entities;

import jakarta.persistence.*;

@Entity

@Table(name = "cartProduct")
public class CartProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;
    private int price;
    private String owner;
    //relationship with cart
    @ManyToOne
    @JoinColumn(name = "id_cart")
    private CartEntity cart;
    //relationship with product
    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product;

    //logic
    public CartProductEntity() {
    }

    public CartProductEntity(long id, int quantity, int price, String owner, CartEntity cart, ProductEntity product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.owner = owner;
        this.cart = cart;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}

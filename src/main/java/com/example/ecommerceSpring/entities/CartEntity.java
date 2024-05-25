package com.example.ecommerceSpring.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carts")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Relationships
    //Rel with user
    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;
    //Rel with CartProduct
    @OneToMany(mappedBy = "cart")
    private List<CartProductEntity> cartProducts;

    //logic

    public CartEntity() {
    }

    public CartEntity(long id, UserEntity user) {
        this.id = id;
        this.user = user;
    }

    public CartEntity(long id, UserEntity user, List<CartProductEntity> cartProducts) {
        this.id = id;
        this.user = user;
        this.cartProducts = cartProducts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<CartProductEntity> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductEntity> cartProducts) {
        this.cartProducts = cartProducts;
    }
}

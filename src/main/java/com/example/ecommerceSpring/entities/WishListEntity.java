package com.example.ecommerceSpring.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "wishList")
public class WishListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //relationships
    //rel with user
    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;
    //rel with wishlist
    @OneToMany(mappedBy = "wishlist")
    private List<WishListProductEntity> wishListProducts;
}

package com.example.ecommerceSpring.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "whishListProduct")
public class WishListProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_wishList")
    private WishListEntity wishlist;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product;
}

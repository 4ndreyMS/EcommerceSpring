package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListProductRepository extends JpaRepository<WishListEntity, Long> {
}

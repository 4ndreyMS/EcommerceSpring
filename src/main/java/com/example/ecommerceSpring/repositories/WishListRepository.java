package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishListEntity, Long> {
}

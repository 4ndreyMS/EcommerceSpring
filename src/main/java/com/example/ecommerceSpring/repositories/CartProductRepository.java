package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
}

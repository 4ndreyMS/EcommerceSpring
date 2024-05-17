package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}

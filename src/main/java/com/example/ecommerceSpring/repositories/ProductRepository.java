package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}

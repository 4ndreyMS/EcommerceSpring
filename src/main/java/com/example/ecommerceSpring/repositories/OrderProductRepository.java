package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {
}

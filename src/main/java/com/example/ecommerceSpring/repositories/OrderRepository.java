package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}

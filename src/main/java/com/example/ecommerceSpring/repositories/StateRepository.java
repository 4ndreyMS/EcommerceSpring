package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity, Long> {
}

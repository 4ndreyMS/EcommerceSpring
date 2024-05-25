package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    @Query("SELECT c FROM CartEntity c WHERE c.user.id = :userId")
    CartEntity findByUserId(@Param("userId") long userId);
}

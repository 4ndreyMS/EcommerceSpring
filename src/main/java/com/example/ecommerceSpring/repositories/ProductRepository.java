package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    public List<ProductEntity> getProductEntityByFeaturedStatusTrueAndDeletedStatusFalse();
}

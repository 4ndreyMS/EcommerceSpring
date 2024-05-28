package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.ProductEntity;
import com.example.ecommerceSpring.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    public List<ProductEntity> getProductEntityByFeaturedStatusTrueAndDeletedStatusFalse();

    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.deletedStatus = :deleted WHERE p.id = :id")
    int softDelete(@Param("deleted") boolean deleted, @Param("id") Long orderId);

    public List<ProductEntity> getProductEntityByDeletedStatusFalse();

}

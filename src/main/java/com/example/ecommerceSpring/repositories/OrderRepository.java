package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.CartEntity;
import com.example.ecommerceSpring.entities.OrderEntity;
import com.example.ecommerceSpring.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    public List<OrderEntity> findByUser_Id(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE OrderEntity o SET o.orderStatus = :orderStatus WHERE o.id = :id")
    int updateOrderStatus(@Param("orderStatus") OrderStatusEnum orderStatus, @Param("id") Long orderId);
}
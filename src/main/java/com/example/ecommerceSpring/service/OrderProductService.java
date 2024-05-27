package com.example.ecommerceSpring.service;

import com.example.ecommerceSpring.entities.CartProductEntity;
import com.example.ecommerceSpring.entities.OrderProductEntity;
import com.example.ecommerceSpring.repositories.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderProductService {
    @Autowired
    private OrderProductRepository orderProductRepository;

    public OrderProductEntity save(OrderProductEntity orderProductTosave) {
        return orderProductRepository.save(orderProductTosave);
    }

    public List<OrderProductEntity> save(List<OrderProductEntity> orderProductEntities) {
        return orderProductRepository.saveAll(orderProductEntities);
    }
}

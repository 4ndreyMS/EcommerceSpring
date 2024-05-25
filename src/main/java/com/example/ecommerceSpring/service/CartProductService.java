package com.example.ecommerceSpring.service;

import com.example.ecommerceSpring.entities.CartProductEntity;
import com.example.ecommerceSpring.repositories.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartProductService {
    @Autowired
    private CartProductRepository cartproductRepository;

    public CartProductEntity findByProductUserCart(Long productId, String userId, Long cartId) {
        return cartproductRepository.findCartProductEntitiesByProduct_IdAndCart_IdAndOwner(productId, cartId, userId);
    }

    public List<CartProductEntity> findByCartAndOwner(Long cartId, String userId) {
        return cartproductRepository.findProductsByCart_IdAndOwner(cartId, userId);
    }

    public boolean deleteCartItem(long cartId, String ownerId, long productId) {
        return cartproductRepository.deleteByCart_IdAndOwnerAndProduct_Id(ownerId, cartId, productId) > 0;
    }

    public boolean deleteAllInCart(long cartId, String ownerId) {
        return cartproductRepository.deleteByCart_IdAndAndOwner(cartId, ownerId) > 0;
    }

}

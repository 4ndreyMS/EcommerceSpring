package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
    public CartProductEntity findCartProductEntitiesByProduct_IdAndCart_IdAndOwner(Long product_Id, Long cart_Id, String owner);

    public List<CartProductEntity> findProductsByCart_IdAndOwner(Long cart_Id, String owner);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartProductEntity cp WHERE cp.owner = :owner AND cp.cart.id = :idCart AND cp.product.id = :idProduct")
    int deleteByCart_IdAndOwnerAndProduct_Id(@Param("owner") String owner, @Param("idCart") Long idCart, @Param("idProduct") Long idProduct);


    int deleteByCart_IdAndAndOwner(Long cart_Id, String owner);
}

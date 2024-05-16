package com.example.ecommerceSpring.repositories;

import com.example.ecommerceSpring.entities.RoleEntity;
import com.example.ecommerceSpring.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(RoleEnum name);
}

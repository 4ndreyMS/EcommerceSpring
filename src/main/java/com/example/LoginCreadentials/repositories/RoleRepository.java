package com.example.LoginCreadentials.repositories;

import com.example.LoginCreadentials.entities.RoleEntity;
import com.example.LoginCreadentials.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(RoleEnum name);
}

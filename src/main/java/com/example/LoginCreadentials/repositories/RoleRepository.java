package com.example.LoginCreadentials.repositories;

import com.example.LoginCreadentials.entities.Role;
import com.example.LoginCreadentials.entities.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}

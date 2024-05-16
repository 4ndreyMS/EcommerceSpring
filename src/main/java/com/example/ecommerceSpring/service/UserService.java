package com.example.ecommerceSpring.service;

import com.example.ecommerceSpring.dtos.RegisterUserDto;
import com.example.ecommerceSpring.dtos.UserDto;
import com.example.ecommerceSpring.entities.RoleEntity;
import com.example.ecommerceSpring.entities.RoleEnum;
import com.example.ecommerceSpring.entities.UserEntity;
import com.example.ecommerceSpring.repositories.RoleRepository;
import com.example.ecommerceSpring.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUserEntity = (UserEntity) authentication.getPrincipal();
        return convertToUserDto(currentUserEntity);
    }

    public List<UserDto> allUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        userRepository.findAll().forEach(entity -> userDtos.add(convertToUserDto(entity)));
        return userDtos;
    }

    public UserDto createAdministrator(RegisterUserDto input) {
        Optional<RoleEntity> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new UserEntity()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        user = userRepository.save(user);
        return convertToUserDto(user);
    }


    private UserDto convertToUserDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

}

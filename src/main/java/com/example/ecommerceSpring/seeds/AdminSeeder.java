package com.example.ecommerceSpring.seeds;

import com.example.ecommerceSpring.dtos.users.RegisterUserDto;
import com.example.ecommerceSpring.entities.RoleEntity;
import com.example.ecommerceSpring.enums.RoleEnum;
import com.example.ecommerceSpring.entities.UserEntity;
import com.example.ecommerceSpring.repositories.RoleRepository;
import com.example.ecommerceSpring.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    @PostConstruct
    public void init() {
        // Your initialization code here
        this.createSuperAdministrator();
    }


    public void createSuperAdministrator() {
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setFullName("Super Admin").setEmail("super.admin@email.com").setPassword("123456");

        Optional<RoleEntity> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<UserEntity> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new UserEntity()
                .setFullName(userDto.getFullName())
                .setEmail(userDto.getEmail())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setRole(optionalRole.get());

        userRepository.save(user);
    }
}

package com.example.ecommerceSpring.service;

import com.example.ecommerceSpring.dtos.users.LoginUserDto;
import com.example.ecommerceSpring.dtos.users.RegisterUserDto;
import com.example.ecommerceSpring.entities.RoleEntity;
import com.example.ecommerceSpring.enums.RoleEnum;
import com.example.ecommerceSpring.entities.UserEntity;
import com.example.ecommerceSpring.exception.CustomException;
import com.example.ecommerceSpring.repositories.RoleRepository;
import com.example.ecommerceSpring.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper modelMapper;

    public AuthenticationService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterUserDto signup(RegisterUserDto input) {
        Optional<RoleEntity> optionalRole = roleRepository.findByName(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new UserEntity()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new CustomException("An account with this email address already exists. Please log in or choose a different email.", HttpStatus.CONFLICT);
        }

        user = userRepository.save(user);
        return convertToRegisterUserDto(user);
    }

    public UserEntity authenticate(LoginUserDto input) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new CustomException(e.getMessage(), HttpStatus.FORBIDDEN);
        }

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<UserEntity> allUsers() {
        List<UserEntity> userEntities = new ArrayList<>();

        userRepository.findAll().forEach(userEntities::add);

        return userEntities;
    }

    private RegisterUserDto convertToRegisterUserDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, RegisterUserDto.class);
    }

    public static boolean isValidPassword(String password) {
        // Define the regex pattern
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

        // Compile the pattern
        Pattern regex = Pattern.compile(pattern);

        // Match the password against the pattern
        Matcher matcher = regex.matcher(password);

        return matcher.matches();
    }
}

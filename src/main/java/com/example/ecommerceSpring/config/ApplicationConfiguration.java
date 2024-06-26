package com.example.ecommerceSpring.config;

import com.example.ecommerceSpring.repositories.CartProductRepository;
import com.example.ecommerceSpring.repositories.UserRepository;
import com.example.ecommerceSpring.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfiguration {
    private final UserRepository userRepository;

    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryService();
    }

    @Bean
    public ProductService productService() {
        return new ProductService();
    }

    @Bean
    public CartService cartService() {
        return new CartService();
    }

    @Bean
    public CartProductService cartProductService() {
        return new CartProductService();
    }

    @Bean
    public OrderService orderService() {
        return new OrderService();
    }

    @Bean
    public OrderProductService orderProductService() {
        return new OrderProductService();
    }

    @Bean
    public StateService stateService() {
        return new StateService();
    }

}


package com.example.ecommerceSpring.service;

import com.example.ecommerceSpring.dtos.ProductDto;
import com.example.ecommerceSpring.dtos.users.UserDto;
import com.example.ecommerceSpring.entities.ProductEntity;
import com.example.ecommerceSpring.entities.UserEntity;
import com.example.ecommerceSpring.exception.CustomException;
import com.example.ecommerceSpring.repositories.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    private ProductEntity convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, ProductEntity.class);
    }

    private ProductDto convertToDto(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDto.class);
    }

    public ProductService() {
    }

    public ProductDto save(ProductDto productDto) {
        if (StringUtils.isEmpty(productDto.getName()) || StringUtils.isEmpty(productDto.getDescription()) ||
                StringUtils.isEmpty(productDto.getImage()) || StringUtils.isEmpty(Double.toString(productDto.getPrice())) ||
                StringUtils.isEmpty(productDto.getSummary()) || StringUtils.isEmpty(Integer.toString(productDto.getStockQuantity())) ||
                StringUtils.isEmpty(productDto.getCategory().toString())) {
            throw new CustomException("No empty fields are acepted", HttpStatus.BAD_REQUEST);
        }
        ProductEntity product = productRepository.save(productDto.toEntity());
        return convertToDto(product);
    }

    public ProductDto findById(long id) {
        ProductEntity product = productRepository.findById(id).orElse(null);
        return convertToDto(product);
    }

    public boolean deleteById(long id) {
        productRepository.deleteById(id);
        return true;
    }

    public List<ProductDto> findAll() {
        List<ProductEntity> productEntities = productRepository.getProductEntityByDeletedStatusFalse();
        List<ProductDto> productDtos = new ArrayList<>();
        productEntities.forEach(productEntity -> {
            productDtos.add(convertToDto(productEntity));
        });
        return productDtos;
    }

    public List<ProductDto> getAllFeatured() {
        List<ProductEntity> productEntities = productRepository.getProductEntityByFeaturedStatusTrueAndDeletedStatusFalse();
        List<ProductDto> productDtos = new ArrayList<>();
        productEntities.forEach(productEntity -> {
            productDtos.add(convertToDto(productEntity));
        });
        return productDtos;
    }

    public boolean exist(ProductDto productDto) {
        return productRepository.existsById(productDto.getId());
    }

    public boolean softDelete(ProductDto productDto) {
        return productRepository.softDelete(productDto.isDeletedStatus(), productDto.getId()) > 0;
    }
}

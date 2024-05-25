package com.example.ecommerceSpring.service;

import com.example.ecommerceSpring.dtos.CategoryDto;
import com.example.ecommerceSpring.enums.CategoryEnum;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    public String extractEnumVal(CategoryEnum categoryEnum) {
        switch (categoryEnum) {
            case BEDROOM:
                return "Bedroom";
            case LIVING_ROOM:
                return "Living Room";
            case KITCHEN:
                return "Kitchen";
            case OUTDOOR:
                return "Outdoor";
            case BATHROOM:
                return "Bathroom";
            default:
                return "Unknown"; // Handle any other cases not explicitly covered
        }
    }

    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryList = new ArrayList<>();

        for (CategoryEnum category : CategoryEnum.values()) {
            categoryList.add(new CategoryDto(extractEnumVal(category), category));
            System.out.println("Enum value: " + category);
        }
        return categoryList;
    }
}



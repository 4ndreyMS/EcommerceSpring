package com.example.ecommerceSpring.dtos;

import com.example.ecommerceSpring.enums.CategoryEnum;

public class CategoryDto {
    private String categoryName;
    private CategoryEnum categoryEnum;

    public CategoryDto(String categoryName, CategoryEnum categoryEnum) {
        this.categoryName = categoryName;
        this.categoryEnum = categoryEnum;
    }

    public CategoryDto() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }
}

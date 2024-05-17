package com.example.ecommerceSpring.entities;

import com.example.ecommerceSpring.enums.CategoryEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String summary;
    private double price;
    private int stockQuantity;
    private String image;
    private boolean isFeatured;
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    //rel with CartProduct
    @OneToMany(mappedBy = "product")
    private List<CartProductEntity> cartProducts;
    //rel with WishListProduct
    @OneToMany(mappedBy = "product")
    private List<WishListProductEntity> wishListProducts;

    @OneToMany(mappedBy = "product")
    private List<OrderProductEntity> orderProducts;

    //logic

    public ProductEntity() {
    }

    public ProductEntity(long id, String name, String description, String summary, double price, int stockQuantity, String image, boolean isFeatured, CategoryEnum category, List<CartProductEntity> cartProducts, List<WishListProductEntity> wishListProducts, List<OrderProductEntity> orderProducts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.image = image;
        this.isFeatured = isFeatured;
        this.category = category;
        this.cartProducts = cartProducts;
        this.wishListProducts = wishListProducts;
        this.orderProducts = orderProducts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public List<CartProductEntity> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductEntity> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public List<WishListProductEntity> getWishListProducts() {
        return wishListProducts;
    }

    public void setWishListProducts(List<WishListProductEntity> wishListProducts) {
        this.wishListProducts = wishListProducts;
    }

    public List<OrderProductEntity> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductEntity> orderProducts) {
        this.orderProducts = orderProducts;
    }
}

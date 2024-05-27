package com.example.ecommerceSpring.dtos.Cart;

import com.example.ecommerceSpring.entities.UserEntity;
import com.example.ecommerceSpring.enums.OrderStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class OrderDto {
    private long id;
    private OrderStatusEnum orderStatus;
    //address form
    private String addres1;
    private String addres2;
    private String city;
    private String state;
    private String zipCode;
    //card info
    private String cardNumber;
    private String cardType;
    private String expiryDate;
    private UserEntity user;

    public OrderDto() {
    }

    public OrderDto(UserEntity user, String expiryDate, String cardType, String cardNumber, String zipCode, String state, String city, String addres2, String addres1, OrderStatusEnum orderStatus, long id) {
        this.user = user;
        this.expiryDate = expiryDate;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.addres2 = addres2;
        this.addres1 = addres1;
        this.orderStatus = orderStatus;
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddres1() {
        return addres1;
    }

    public void setAddres1(String addres1) {
        this.addres1 = addres1;
    }

    public String getAddres2() {
        return addres2;
    }

    public void setAddres2(String addres2) {
        this.addres2 = addres2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}

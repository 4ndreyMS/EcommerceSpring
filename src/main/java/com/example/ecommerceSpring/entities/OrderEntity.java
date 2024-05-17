package com.example.ecommerceSpring.entities;

import com.example.ecommerceSpring.enums.OrderStatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
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
    //relationships
    //rel to users
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;
    //rel to orders
    @OneToMany(mappedBy = "order")
    List<OrderProductEntity> orderProduct;

    //logic
    public OrderEntity() {
    }

    public OrderEntity(long id, String addres1, String addres2, String city, String state, String zipCode, String cardNumber, String cardType, String expiryDate, UserEntity user, List<OrderProductEntity> orderProduct) {
        this.id = id;
        this.addres1 = addres1;
        this.addres2 = addres2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.user = user;
        this.orderProduct = orderProduct;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<OrderProductEntity> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(List<OrderProductEntity> orderProduct) {
        this.orderProduct = orderProduct;
    }
}

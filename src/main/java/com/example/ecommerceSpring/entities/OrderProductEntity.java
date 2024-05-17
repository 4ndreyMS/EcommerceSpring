package com.example.ecommerceSpring.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "orderProduct")
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;

    //relationships
    //rel with order
    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;
    //relationship with product
    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product;

    //logic
    public OrderProductEntity() {
    }

    public OrderProductEntity(long id, int quantity, OrderEntity order, ProductEntity product) {
        this.id = id;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}

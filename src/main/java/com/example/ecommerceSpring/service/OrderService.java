package com.example.ecommerceSpring.service;

import com.example.ecommerceSpring.dtos.Cart.CartItemDto;
import com.example.ecommerceSpring.dtos.Orders.OrderDto;
import com.example.ecommerceSpring.dtos.Orders.OrderItemDto;
import com.example.ecommerceSpring.dtos.Orders.OrderWithUserDto;
import com.example.ecommerceSpring.dtos.ProductDto;
import com.example.ecommerceSpring.dtos.users.UserDto;
import com.example.ecommerceSpring.entities.CartProductEntity;
import com.example.ecommerceSpring.entities.OrderEntity;
import com.example.ecommerceSpring.entities.OrderProductEntity;
import com.example.ecommerceSpring.entities.UserEntity;
import com.example.ecommerceSpring.enums.OrderStatusEnum;
import com.example.ecommerceSpring.enums.RoleEnum;
import com.example.ecommerceSpring.exception.CustomException;
import com.example.ecommerceSpring.repositories.OrderRepository;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService<T> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderProductService orderProductService;

    private UserDto currentUser;


    public OrderService() {
        //we need to first validate if the logged user have a or
    }

    public boolean createOrder(OrderDto order) {
        currentUser = userService.authenticatedUser();
        //validations
        if (currentUser == null) {
            return false;
        }
        if (orderRepository.findById(order.getId()).isPresent()) {
            throw new CustomException("Order already exist", HttpStatus.CONFLICT);
        }
        // first we create the order with shipping and card info
        if (StringUtils.isBlank(order.getOrderStatus().toString()) ||
                StringUtils.isBlank(order.getCity()) ||
                StringUtils.isBlank(order.getState()) ||
                StringUtils.isBlank(order.getZipCode()) ||
                StringUtils.isBlank(order.getCardNumber()) ||
                StringUtils.isBlank(order.getAddres1()) ||
                StringUtils.isBlank(order.getAddres2())) {
            throw new CustomException("No empty fields acepted", HttpStatus.BAD_REQUEST);
        }

        if (order.getOrderStatus() == null) {
            throw new CustomException("Invalid order status", HttpStatus.BAD_REQUEST);
        }
        OrderEntity orderInfoEntity = modelMapper.map(order, OrderEntity.class);
        orderInfoEntity.setOrderStatus(order.getOrderStatus());
        orderInfoEntity.setUser(modelMapper.map(currentUser, UserEntity.class));

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        orderInfoEntity.setCreationDate(formatter.format(date));
        OrderEntity newOrder = orderRepository.save(orderInfoEntity);
        //sencond we transfer the items from cart to the middle table of order
        List<OrderProductEntity> cartItemsToOrderItem = getOrderProductEntities(newOrder);

        if (!orderProductService.save(cartItemsToOrderItem).isEmpty()) {
            cartService.clearCart();
            return true;
        }
        return false;
    }

    private List<OrderProductEntity> getOrderProductEntities(OrderEntity newOrder) {
        List<CartProductEntity> cartItems = cartService.getCartProductItems();
        //we have to valdiate if the user have item in cart
        if (cartItems == null || cartItems.isEmpty()) {
            throw new CustomException("After place an order you have to add products in your cart", HttpStatus.BAD_REQUEST);
        }

        List<OrderProductEntity> cartItemsToOrderItem = new ArrayList<>();
        for (CartProductEntity cartItem : cartItems) {
            OrderProductEntity newOrderItem = new OrderProductEntity(0, cartItem.getQuantity(), newOrder, cartItem.getProduct());
            cartItemsToOrderItem.add(newOrderItem);
        }
        return cartItemsToOrderItem;
    }

    private OrderStatusEnum statusConverter(String status) {

        switch (status.toLowerCase()) {
            case "confirmed":
                return OrderStatusEnum.CONFIRMED;
            case "canceled":
                return OrderStatusEnum.CANCELED;
            case "pending":
                return OrderStatusEnum.PENDING;
            default:
                throw new IllegalArgumentException("Unknown order status: " + status);
        }
    }

    public List<OrderDto> getCurrentUserOrdersInfo() {
        currentUser = userService.authenticatedUser();
        return convertOrdersToDto(orderRepository.findByUser_Id(currentUser.getId()));
    }

    public List<OrderDto> getAllOrdersInfo() {
        currentUser = userService.authenticatedUser();
        if (currentUser.getRole().getName().equals(RoleEnum.USER)) {
            return new ArrayList<>();
        }
        return convertOrdersToDto(orderRepository.findAll());
    }

    private List<OrderDto> convertOrdersToDto(List<OrderEntity> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (OrderEntity order : orders) {
            OrderDto orderConverted = modelMapper.map(order, OrderDto.class);

            List<CartItemDto> items = new ArrayList<>();
            order.getOrderProduct().forEach(item -> {
                items.add(new CartItemDto(modelMapper.map(item.getProduct(), ProductDto.class), item.getQuantity(), 0));
            });
            orderConverted.setOrderedItems(items);
            orderDtos.add(orderConverted);
        }

        return orderDtos;
    }

    public OrderWithUserDto findById(Long id) {
        currentUser = userService.authenticatedUser();
        OrderEntity order = orderRepository.findById(id).orElse(null);
        if (null == order) {
            return null;
        }

        OrderWithUserDto orderDto = modelMapper.map(order, OrderWithUserDto.class);
        List<OrderItemDto> items = new ArrayList<>();
        order.getOrderProduct().forEach(item -> {
            items.add(new OrderItemDto(modelMapper.map(item.getProduct(), ProductDto.class), item.getQuantity(), 0, modelMapper.map(order.getUser(), UserDto.class)));
        });
        orderDto.setOrderedItems(items);

        return orderDto;
    }

    public boolean updateOrderStatus(OrderDto orderToUpdate) {
        if (!orderRepository.existsById(orderToUpdate.getId())) {
            throw new CustomException("Order not found", HttpStatus.NOT_FOUND);
        }
        if (currentUser.getRole().getName().equals(RoleEnum.USER)) {
            throw new CustomException("Forbiden action", HttpStatus.FORBIDDEN);
        }

        int response = orderRepository.updateOrderStatus(orderToUpdate.getOrderStatus(), orderToUpdate.getId());
        if (response > 0) {
            return true;
        }
        return false;
    }
}

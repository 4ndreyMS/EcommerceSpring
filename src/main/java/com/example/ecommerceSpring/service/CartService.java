package com.example.ecommerceSpring.service;

import com.example.ecommerceSpring.dtos.Cart.CartItemDto;
import com.example.ecommerceSpring.dtos.ProductDto;
import com.example.ecommerceSpring.dtos.users.InsertDto;
import com.example.ecommerceSpring.dtos.users.UserDto;
import com.example.ecommerceSpring.entities.*;
import com.example.ecommerceSpring.exception.CustomException;
import com.example.ecommerceSpring.repositories.CartProductRepository;
import com.example.ecommerceSpring.repositories.CartRepository;
import com.example.ecommerceSpring.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartProductService cartProductService;

    private UserDto currentUser;

    public CartService() {
    }

    /*
     * this method add and updates the item in the cart
     */
    public CartItemDto addToCart(InsertDto insertDto) {
        currentUser = userService.authenticatedUser();

        //validate if the provided product exist
        ProductDto product = productService.findById(insertDto.getProduct().getId());
        if (null == product) {
            throw new CustomException("Provided product not found", HttpStatus.CONFLICT);
        }
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);

        //validate if the user have cart
        CartEntity cartEntity;
        try {
            cartEntity = cartRepository.findByUserId(this.currentUser.getId());
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.CONFLICT);
        }
        if (null == cartEntity) {
            //if not create a cart
            UserEntity userEntity = modelMapper.map(this.currentUser, UserEntity.class);
            cartEntity = new CartEntity(0, userEntity);
            cartEntity = cartRepository.save(cartEntity);
        }

        /*
         * After the creation of the cart, we need to insert into the middle table.
         * */
        //Validate if in the midle table the user have the product with the provided product
        CartProductEntity cartProduct = cartProductService.findByProductUserCart(productEntity.getId(), Long.toString(this.currentUser.getId()), cartEntity.getId());
        //if doent exist
        if (null == cartProduct) {
            cartProduct = new CartProductEntity(0, 1, insertDto.getProduct().getPrice(), Long.toString(this.currentUser.getId()), cartEntity, productEntity);
        } else {
            cartProduct.setQuantity(insertDto.getQuantity());
        }

        CartProductEntity newCartProductEntity = cartProductRepository.save(cartProduct);

        ProductDto newProduct = modelMapper.map(newCartProductEntity.getProduct(), ProductDto.class);

        return new CartItemDto(newProduct, newCartProductEntity.getQuantity(), newProduct.getPrice() * newCartProductEntity.getQuantity());
    }

    /*
     * this method retrieves all items of the cart of the current usr
     * No params expected
     * */
    public List<CartItemDto> getCartItems() {
        currentUser = userService.authenticatedUser();

        List<CartItemDto> cartItems = new ArrayList<>();

        try {
            CartEntity cartEntity = cartRepository.findByUserId(this.currentUser.getId());

            if (null != cartEntity) {

                cartProductService.findByCartAndOwner(cartEntity.getId(), Long.toString(this.currentUser.getId())).forEach(item -> {
                    ProductDto product = modelMapper.map(item.getProduct(), ProductDto.class);
                    cartItems.add(new CartItemDto(product, item.getQuantity(), product.getPrice() * item.getQuantity()));
                });
                return cartItems;
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return cartItems;
    }

    public List<CartProductEntity> getCartProductItems() {
        currentUser = userService.authenticatedUser();
        List<CartProductEntity> cartProduct = new ArrayList<>();
        try {
            CartEntity cartEntity = cartRepository.findByUserId(this.currentUser.getId());

            if (null != cartEntity) {

                cartProduct = cartProductService.findByCartAndOwner(cartEntity.getId(), Long.toString(this.currentUser.getId()));
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return cartProduct;
    }

    /*
     * Delete a product from the cart based on a product id
     * @Params: product id
     * */
    public boolean deleteCartItem(long productId) {
        currentUser = userService.authenticatedUser();

        try {
            CartEntity cartEntity = cartRepository.findByUserId(this.currentUser.getId());
            ProductDto product = productService.findById(productId);


            if (null != product && null != cartEntity) {
                return cartProductService.
                        deleteCartItem(cartEntity.getId(), Long.toString(this.currentUser.getId()), product.getId());
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return false;
    }

    /*
     * this method retrieves all items of the cart of the current usr
     * */
    public boolean clearCart() {
        currentUser = userService.authenticatedUser();

        try {
            CartEntity cartEntity = cartRepository.findByUserId(this.currentUser.getId());

            if (null != this.currentUser && null != cartEntity) {
                return cartProductService.deleteAllInCart(cartEntity.getId(), Long.toString(this.currentUser.getId()));
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return false;
    }

    public long cartItemsAmount() {
        long counter = 0;
        try {
            List<CartItemDto> cartItemDtos = getCartItems();
            for (CartItemDto cartItemDto : cartItemDtos) {
                counter += cartItemDto.getQuantity();
            }

        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return counter;
    }
}

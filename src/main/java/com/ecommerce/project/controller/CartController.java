package com.ecommerce.project.controller;

import com.ecommerce.project.payload.CartDTO;
import com.ecommerce.project.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class CartController {

    private CartService cartService;
    @PostMapping("/carts/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long productId,
                                                    @PathVariable Integer quantity)
    {
        CartDTO cartDTO = cartService.addProductToCart(productId, quantity);

        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.CREATED);
    }
}

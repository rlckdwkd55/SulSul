package com.sulsulmarket.sulsul.cart.controller;

import com.sulsulmarket.sulsul.cart.service.CartService;
import com.sulsulmarket.sulsul.dto.cart.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/api/cart/select")
    public ResponseEntity<Object> getCartListByMemberId(@RequestBody Cart cart) {

        List<Cart> cartList = cartService.getCartListByMemberId(cart.getMEMBER_ID());

        return ResponseEntity.status(HttpStatus.OK).body(cartList);
    }

    @PostMapping("/api/cart/add")
    public ResponseEntity<Object> addCartByMemberIdAndProduct(@RequestBody Cart cart) {

        try {
            cartService.addCartByMemberIdAndProduct(cart.getMEMBER_ID(), cart.getPRODUCT_NO(), cart.getCART_AMOUNT());
            return ResponseEntity.status(HttpStatus.OK).body("장바구니 추가 성공!");
        } catch (Exception e) {
            log.error("Cart Add Fail ! ! ! {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/cart/remove")
    public ResponseEntity<Object> deleteCartByMemberIdAndProductNo(@RequestBody Cart cart) {

        try {
            cartService.deleteCartByMemberId(cart.getMEMBER_ID(), cart.getPRODUCT_NO());
            return ResponseEntity.status(HttpStatus.OK).body("장바구니 삭제 성공");
        } catch (Exception e) {
            log.error("Cart Delete Is Fail ! ! !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

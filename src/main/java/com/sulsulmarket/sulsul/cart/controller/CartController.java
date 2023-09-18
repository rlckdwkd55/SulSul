package com.sulsulmarket.sulsul.cart.controller;

import com.sulsulmarket.sulsul.cart.service.CartService;
import com.sulsulmarket.sulsul.dto.cart.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {
    // TEST
    @Autowired
    private CartService cartService;

    @PostMapping("/info")
    public ResponseEntity<Object> getCartListByMemberEmail(@RequestBody Map<String, String> param) {

        try {
            List<Cart> cartList = cartService.getCartListByMemberId(param.get("email"));
            return new ResponseEntity<>(cartList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("get cart List Exception.", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCartByMemberIdAndProduct(@RequestBody Cart cart) {

        try {
            cartService.addCartByMemberIdAndProduct(cart.getMEMBER_EMAIL(), cart.getPRODUCT_NO(), cart.getQUANTITY());
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("cart Add Exception..", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<Object> deleteCartByMemberIdAndProductNo(@RequestBody Cart cart) {

        try {
            cartService.deleteCartByMemberId(cart.getMEMBER_EMAIL(), cart.getPRODUCT_NO());
            return ResponseEntity.status(HttpStatus.OK).body("장바구니 삭제 성공");
        } catch (Exception e) {
            log.error("Cart Delete Is Fail ! ! !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

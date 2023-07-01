package com.sulsulmarket.sulsul.cart.controller;

import com.sulsulmarket.sulsul.cart.dto.CartDTO;
import com.sulsulmarket.sulsul.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/api/cart/select")
    public ResponseEntity<Object> getCartListByMemberId(@RequestBody CartDTO cartDTO) {

        CartDTO cart = cartService.getCartListByMemberId(cartDTO.getMEMBER_ID());
        //TODO cart에 담기는 product_no로 product 객체 리스트로 반환하기

        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @PostMapping("/api/cart/add")
    public ResponseEntity<Object> addCartByMemberIdAndProduct(@RequestBody CartDTO cartDTO) {

        CartDTO cart = cartService.addCartByMemberIdAndProduct(cartDTO.getMEMBER_ID(), cartDTO.getPRODUCT_NO(), cartDTO.getCART_AMOUNT());

        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }
}

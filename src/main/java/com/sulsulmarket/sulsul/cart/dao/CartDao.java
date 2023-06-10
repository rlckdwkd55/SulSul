package com.sulsulmarket.sulsul.cart.dao;

import com.sulsulmarket.sulsul.cart.dto.CartDTO;
import com.sulsulmarket.sulsul.cart.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao implements CartMapper {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public CartDTO getCartListByMemberId(String memberId) {
        return cartMapper.getCartListByMemberId(memberId);
    }

    @Override
    public int addCartByMemberIdAndProduct(String memberId, int productNo, int cartAmount) {
        return cartMapper.addCartByMemberIdAndProduct(memberId, productNo, cartAmount);
    }
}

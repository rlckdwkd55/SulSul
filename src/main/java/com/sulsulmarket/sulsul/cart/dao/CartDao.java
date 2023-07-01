package com.sulsulmarket.sulsul.cart.dao;

import com.sulsulmarket.sulsul.cart.dto.CartDTO;
import com.sulsulmarket.sulsul.cart.dto.ProductImage;
import com.sulsulmarket.sulsul.cart.dto.ProductJoinDTO;
import com.sulsulmarket.sulsul.cart.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDao implements CartMapper {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<CartDTO> getCartListByMemberId(String memberId) {
        return cartMapper.getCartListByMemberId(memberId);
    }

    @Override
    public int addCartByMemberIdAndProduct(String memberId, int productNo, int cartAmount) {
        return cartMapper.addCartByMemberIdAndProduct(memberId, productNo, cartAmount);
    }

    @Override
    public ProductJoinDTO productJoinImageList(int productNo) {
        return cartMapper.productJoinImageList(productNo);
    }

    @Override
    public ProductImage getProductImage(int productNo) {
        return cartMapper.getProductImage(productNo);
    }
}

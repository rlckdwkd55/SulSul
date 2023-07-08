package com.sulsulmarket.sulsul.cart.dao;

import com.sulsulmarket.sulsul.cart.dto.CartDTO;
import com.sulsulmarket.sulsul.cart.dto.ProductImage;
import com.sulsulmarket.sulsul.cart.dto.ProductDTO;
import com.sulsulmarket.sulsul.cart.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDao implements CartMapper {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public int addCartByMemberIdAndProduct(String memberId, int productNo, int cartAmount) {
        return cartMapper.addCartByMemberIdAndProduct(memberId, productNo, cartAmount);
    }

    @Override
    public CartDTO getCartByMemberIdAndProductNo(String memberId, int productNo) {
        return cartMapper.getCartByMemberIdAndProductNo(memberId, productNo);
    }

    @Override
    public int updateCartCount(String memberId, int productNo, int cartAmount) {
        return cartMapper.updateCartCount(memberId, productNo, cartAmount);
    }

    @Override
    public int deleteCartByMemberId(String memberId, int productNo) {
        return cartMapper.deleteCartByMemberId(memberId, productNo);
    }

    @Override
    public ProductDTO productListByProductNo(int productNo) {
        return cartMapper.productListByProductNo(productNo);
    }

    @Override
    public ProductImage productImageByProductNo(int productNo) {
        return cartMapper.productImageByProductNo(productNo);
    }

    @Override
    public List<CartDTO> getCartListByMemberId(String memberId) {
        return cartMapper.getCartListByMemberId(memberId);
    }
}

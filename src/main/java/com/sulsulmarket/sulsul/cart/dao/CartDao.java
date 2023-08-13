package com.sulsulmarket.sulsul.cart.dao;

import com.sulsulmarket.sulsul.dto.cart.Cart;
import com.sulsulmarket.sulsul.dto.product.ProductImage;
import com.sulsulmarket.sulsul.dto.product.Product;
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
    public Cart getCartByMemberIdAndProductNo(String memberId, int productNo) {
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
    public Product productListByProductNo(int productNo) {
        return cartMapper.productListByProductNo(productNo);
    }

    @Override
    public ProductImage productImageByProductNo(int productNo) {
        return cartMapper.productImageByProductNo(productNo);
    }

    @Override
    public List<Cart> getCartListByMemberId(String memberId) {
        return cartMapper.getCartListByMemberId(memberId);
    }
}

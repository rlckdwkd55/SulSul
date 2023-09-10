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
    public int addCartByMemberIdAndProduct(String email, int productNo, int quantity) {
        return cartMapper.addCartByMemberIdAndProduct(email, productNo, quantity);
    }

    @Override
    public Cart getCartByMemberIdAndProductNo(String email, int productNo) {
        return cartMapper.getCartByMemberIdAndProductNo(email, productNo);
    }

    @Override
    public int updateCartCount(String email, int productNo, int quantity) {
        return cartMapper.updateCartCount(email, productNo, quantity);
    }

    @Override
    public int deleteCartByMemberId(String email, int productNo) {
        return cartMapper.deleteCartByMemberId(email, productNo);
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
    public List<Cart> getCartListByMemberEmail(String email) {
        return cartMapper.getCartListByMemberEmail(email);
    }
}

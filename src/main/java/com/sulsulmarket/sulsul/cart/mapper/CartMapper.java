package com.sulsulmarket.sulsul.cart.mapper;

import com.sulsulmarket.sulsul.dto.cart.Cart;
import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.dto.product.ProductImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    int addCartByMemberIdAndProduct(String memberId, int productNo, int cartAmount);

    Cart getCartByMemberIdAndProductNo(String memberId, int productNo);

    int updateCartCount(String memberId, int productNo, int cartAmount);

    int deleteCartByMemberId(String memberId, int productNo);

    Product productListByProductNo(int productNo);

    ProductImage productImageByProductNo(int productNo);

    List<Cart> getCartListByMemberId(String memberId);
}
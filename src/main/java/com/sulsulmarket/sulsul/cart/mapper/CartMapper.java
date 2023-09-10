package com.sulsulmarket.sulsul.cart.mapper;

import com.sulsulmarket.sulsul.dto.cart.Cart;
import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.dto.product.ProductImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    int addCartByMemberIdAndProduct(String email, int productNo, int quantity);

    Cart getCartByMemberIdAndProductNo(String email, int productNo);

    int updateCartCount(String email, int productNo, int quantity);

    int deleteCartByMemberId(String email, int productNo);

    Product productListByProductNo(int productNo);

    ProductImage productImageByProductNo(int productNo);

    List<Cart> getCartListByMemberEmail(String email);
}
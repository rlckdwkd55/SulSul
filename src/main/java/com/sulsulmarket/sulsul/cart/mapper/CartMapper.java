package com.sulsulmarket.sulsul.cart.mapper;

import com.sulsulmarket.sulsul.cart.dto.CartDTO;
import com.sulsulmarket.sulsul.cart.dto.ProductImage;
import com.sulsulmarket.sulsul.cart.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    int addCartByMemberIdAndProduct(String memberId, int productNo, int cartAmount);

    CartDTO getCartByMemberIdAndProductNo(String memberId, int productNo);

    int updateCartCount(String memberId, int productNo, int cartAmount);

    int deleteCartByMemberId(String memberId, int productNo);

    ProductDTO productListByProductNo(int productNo);

    ProductImage productImageByProductNo(int productNo);

    List<CartDTO> getCartListByMemberId(String memberId);
}
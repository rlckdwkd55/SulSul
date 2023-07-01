package com.sulsulmarket.sulsul.cart.mapper;

import com.sulsulmarket.sulsul.cart.dto.CartDTO;
import com.sulsulmarket.sulsul.cart.dto.ProductImage;
import com.sulsulmarket.sulsul.cart.dto.ProductJoinDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    List<CartDTO> getCartListByMemberId(String memberId);

    int addCartByMemberIdAndProduct(String memberId, int productNo, int cartAmount);

    ProductJoinDTO productJoinImageList(int productNo);

    ProductImage getProductImage(int productNo);
}

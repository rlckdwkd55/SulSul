package com.sulsulmarket.sulsul.cart.mapper;

import com.sulsulmarket.sulsul.cart.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {

    CartDTO getCartListByMemberId(String memberId);

    int addCartByMemberIdAndProduct(String memberId, int productNo, int cartAmount);
}

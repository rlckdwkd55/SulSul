package com.sulsulmarket.sulsul.cart.service;

import com.sulsulmarket.sulsul.cart.dao.CartDao;
import com.sulsulmarket.sulsul.cart.dto.CartDTO;
import com.sulsulmarket.sulsul.member.dao.MemberDao;
import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import com.sulsulmarket.sulsul.product.dao.ProductDao;
import com.sulsulmarket.sulsul.product.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private ProductDao productDao;

    public CartDTO getCartListByMemberId(String memberId) {

        CartDTO cart = cartDao.getCartListByMemberId(memberId);

        if(cart == null) {
            log.error("[ERROR] GET CART LIST BY MEMBER_ID IS NULL ! ! !");
            throw new NullPointerException("회원 아이디로 장바구니 리스트를 가져오기 실패했습니다.");
        }

        return cart;
    }

    /**
     * 회원 기준 장바구니 추가하는 메서드
     */
    @Transactional
    public CartDTO addCartByMemberIdAndProduct(String memberId, int productNo, int cartAmount) {

        MemberDTO member = memberDao.getMemberById(memberId);
        if (member == null) {
            throw new NullPointerException("회원 아이디가 일치하지 않습니다.");
        }

        Product product = productDao.getProductByProductNo(productNo);
        if (product == null) {
            throw new NullPointerException("해당 상품이 존재하지 않습니다.");
        }

        Integer insertCount = cartDao.addCartByMemberIdAndProduct(memberId, productNo, cartAmount);
        if (insertCount == null || insertCount == 0) {
            throw new NullPointerException("장바구니에 담는 것을 실패하였습니다.");
        }

        return new CartDTO().toDTO(memberId, productNo, cartAmount);
    }
}
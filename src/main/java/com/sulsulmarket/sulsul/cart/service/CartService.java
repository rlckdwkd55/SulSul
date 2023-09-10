package com.sulsulmarket.sulsul.cart.service;

import com.sulsulmarket.sulsul.Util.SulSulUtil;
import com.sulsulmarket.sulsul.cart.dao.CartDao;
import com.sulsulmarket.sulsul.dto.cart.Cart;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.dto.product.ProductImage;
import com.sulsulmarket.sulsul.member.dao.MemberDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private MemberDao memberDao;

    public List<Cart> getCartListByMemberId(String email) {

        if (SulSulUtil.strNullCheck(email)) {
            log.error("member email parameter is null.");
            throw new NullPointerException("이메일 파라미터 값이 없습니다.");
        }

        if (memberDao.getMemberByEmail(email) == null) {
            log.error("member is not found member..");
            throw new NullPointerException("이메일로 해당 회원을 찾을 수 없습니다.");
        }

        List<Cart> cartList = cartDao.getCartListByMemberEmail(email);
        log.debug("cartList size : [{}]", cartList.size());

        if(cartList.size() < 0) {
            log.error("get cart List By member email is fail..");
            throw new IllegalArgumentException("회원 아이디로 장바구니 리스트를 가져오기 실패했습니다.");
        }

        for (Cart cart : cartList) {
            int productNo = cart.getPRODUCT_NO();
            Product product = cartDao.productListByProductNo(productNo);

            if(product == null || Objects.isNull(product)) {
                log.error("Product Is Null");
                throw new NullPointerException("해당 상품은 존재하지 않습니다.");
            }

            cart.setPRODUCT(product);
            ProductImage productImageDto = cartDao.productImageByProductNo(productNo);

            if (productImageDto == null || Objects.isNull(productImageDto)) {
                log.error("ProductImage Is Null");
                throw new NullPointerException("상품 이미지가 없습니다.");
            }

            product.setPRODUCT_IMAGE(productImageDto);
            log.info("ProductImage ==>> [{}]", productImageDto);
            log.info("CART PRODUCT IMAGE CHECK ==>> [{}]", product.getPRODUCT_IMAGE());
            cartList.add(cart);
            log.info("cart Check ! ! !  ==>> [{}]", cart.toString());
        }
        log.info("CartList Check", cartList.toString());
        return cartList;
    }

    /**
     * 회원 기준 장바구니 추가하는 메서드
     */
    @Transactional
    public void addCartByMemberIdAndProduct(String email, int productNo, int cartAmount) {

        checkMemberProductNo(email, productNo);
        /**
         * 해당 멤버 아이디, 프로덕트 번호로 조회 했는데 정보가 없을 경우
         * 처음 추가하는 거라고 판단하여 INSERT 구문 실행
         * 해당 상품이 이미 존재할 경우 UPDATE 구문을 사용하여 카운트를 증가시킴.
         */
        Cart Cart = cartDao.getCartByMemberIdAndProductNo(email, productNo);
        if(Cart == null || Objects.isNull(Cart)) {
            if(cartAmount <= 0) {
                log.error("Insert Is Fail Amount > 0 ! ! !");
                throw new IllegalArgumentException("수량이 0보다 작을 수 없습니다");
            }
            try {
                // INSERT
                cartDao.addCartByMemberIdAndProduct(email, productNo, cartAmount);
                log.info("CART INSERT IS SUCCESS ! ! !");
            } catch (Exception e) {
                log.error("DB CART INSERT FAIL ! ! ! {}", e);
            }
            // Cart에 email, ProductNo 조회 시 있다는 거는 해당 PRODUCT_NO 상품이 존재
        } else {
            if(cartAmount <= 0) {
                log.error("Update Is Fail Amount > 0 ! ! !");
                throw new IllegalArgumentException("수량이 0보다 작을 수 없습니다");
            }
            try {
                // UPDATE
                cartDao.updateCartCount(email, productNo, cartAmount);
                log.info("Cart Product Amount Update Success => PRODUCT : [{}] AMOUNT : [{}]", productNo, cartAmount);
            } catch (Exception e) {
                log.error("DB CART UPDATE FAIL ! ! ! {}", e);
            }
        }
    }

    @Transactional
    public void deleteCartByMemberId(String email, int productNo) {

        checkMemberProductNo(email, productNo);

        try {
            cartDao.deleteCartByMemberId(email, productNo);
            log.info("CART DELETE IS SUCCESS");
        } catch (Exception e) {
            log.error("DB CART DELETE IS FAIL ! ! !");
        }
    }

    public void checkMemberProductNo(String email, int productNo) {

        Member member = memberDao.getMemberByEmail(email);
        if (member == null || Objects.isNull(member)) {
            throw new NullPointerException("회원 아이디가 일치하지 않습니다.");
        }

        Product product = cartDao.productListByProductNo(productNo);
        if (product == null || Objects.isNull(product)) {
            throw new NullPointerException("해당 상품이 존재하지 않습니다.");
        }
    }
}
package com.sulsulmarket.sulsul.cart.service;

import com.sulsulmarket.sulsul.cart.dao.CartDao;
import com.sulsulmarket.sulsul.cart.dto.CartDTO;
import com.sulsulmarket.sulsul.cart.dto.ProductDTO;
import com.sulsulmarket.sulsul.cart.dto.ProductImage;
import com.sulsulmarket.sulsul.member.dao.MemberDao;
import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import com.sulsulmarket.sulsul.product.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private MemberDao memberDao;

    public List<CartDTO> getCartListByMemberId(String memberId) {

        MemberDTO member = memberDao.getMemberById(memberId);
        if (member == null || Objects.isNull(member)) {
            log.error("Not Found Member ! ! !");
            throw new NullPointerException("회원이 없음");
        }

        List<CartDTO> cartDTOList = new ArrayList<>();

        List<CartDTO> cartList = cartDao.getCartListByMemberId(memberId);
        log.info("cartList Size?? {}", cartList.size());

        if(cartList.size() < 0) {
            log.error("[ERROR] GET CART LIST BY MEMBER_ID IS NULL ! ! !");
            throw new NullPointerException("회원 아이디로 장바구니 리스트를 가져오기 실패했습니다.");
        }

        for (CartDTO cart : cartList) {
            int productNo = cart.getPRODUCT_NO();
            log.info("PRODUCT_NO CHECK ++===>>>>>> {}", productNo);
            ProductDTO product = cartDao.productListByProductNo(productNo);

            if(product == null || Objects.isNull(product)) {
                log.error("Product Is Null");
                throw new NullPointerException("해당 상품은 존재하지 않습니다.");
            }

            cart.setPRODUCT(product);
            log.info("CART PRODUCT CHECK ==>> [{}]", cart.getPRODUCT().toString());
            ProductImage productImage = cartDao.productImageByProductNo(productNo);

            if (productImage == null || Objects.isNull(productImage)) {
                log.error("ProductImage Is Null");
                throw new NullPointerException("상품 이미지가 없습니다.");
            }

            product.setPRODUCT_IMAGE(productImage);
            log.info("CART PRODUCT IMAGE CHECK ==>> [{}]", cart.getPRODUCT().getPRODUCT_IMAGE());
            cartDTOList.add(cart);
            log.info("CART_LIST ===>>>> [{}]", cartList.stream().map(Objects::toString).collect(Collectors.joining(",")));
        }
        return cartDTOList;
    }

    /**
     * 회원 기준 장바구니 추가하는 메서드
     */
    @Transactional
    public void addCartByMemberIdAndProduct(String memberId, int productNo, int cartAmount) {

        checkMemberProductNo(memberId, productNo);
        /**
         * 해당 멤버 아이디, 프로덕트 번호로 조회 했는데 정보가 없을 경우
         * 처음 추가하는 거라고 판단하여 INSERT 구문 실행
         */
        CartDTO cartDTO = cartDao.getCartByMemberIdAndProductNo(memberId, productNo);
        if(cartDTO == null || Objects.isNull(cartDTO)) {
            if(cartAmount <= 0) {
                log.error("Insert Is Fail Amount > 0 ! ! !");
                throw new IllegalArgumentException("수량이 0보다 작을 수 없습니다");
            }
            try {
                cartDao.addCartByMemberIdAndProduct(memberId, productNo, cartAmount);
                log.info("CART INSERT IS SUCCESS ! ! !");
            } catch (Exception e) {
                log.error("DB CART INSERT FAIL ! ! ! {}", e);
            }
            // Cart에 MemberId, ProductNo 조회 시 있다는 거는 해당 PRODUCT_NO 상품이 존재
        } else {
            if(cartAmount <= 0) {
                log.error("Update Is Fail Amount > 0 ! ! !");
                throw new IllegalArgumentException("수량이 0보다 작을 수 없습니다");
            }
            try {
                cartDao.updateCartCount(memberId, productNo, cartAmount);
                log.info("Cart Product Amount Update Success => PRODUCT : [{}] AMOUNT : [{}]", productNo, cartAmount);
            } catch (Exception e) {
                log.error("DB CART UPDATE FAIL ! ! ! {}", e);
            }
        }
    }

    @Transactional
    public void deleteCartByMemberId(String memberId, int productNo) {

        checkMemberProductNo(memberId, productNo);

        try {
            cartDao.deleteCartByMemberId(memberId, productNo);
            log.info("CART DELETE IS SUCCESS");
        } catch (Exception e) {
            log.error("DB CART DELETE IS FAIL ! ! !");
        }
    }

    public void checkMemberProductNo(String memberId, int productNo) {

        MemberDTO member = memberDao.getMemberById(memberId);
        if (member == null || Objects.isNull(member)) {
            throw new NullPointerException("회원 아이디가 일치하지 않습니다.");
        }

        ProductDTO product = cartDao.productListByProductNo(productNo);
        if (product == null || Objects.isNull(product)) {
            throw new NullPointerException("해당 상품이 존재하지 않습니다.");
        }
    }
}
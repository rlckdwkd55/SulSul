package com.sulsulmarket.sulsul.cart.dto;

import com.sulsulmarket.sulsul.payment.dto.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
/**
 * 비회원이 메모리에 들고있을 장바구니 클래스
 */
public class CartStorage {

    // 클라이언트의 세션 아이디 : 장바구니 리스트 형태를 담기 위한 Map
    private Map<String, List<CartDTO>> cartItemsBySession;

    // private 선언 이유 외부에서 직접 객체를 생성할 수 없으므로 제어된 인스턴스 생성이 가능함
    // 생성자는 해당 인스턴스가 생성되면서 호출 HashMap 으로 초기화
    private CartStorage() {
        cartItemsBySession = new HashMap<>();
    }

    /**
     * 장바구니에 추가하는 메서드
     */
    //TODO 같은 상품인데 두 번 추가할 경우 수량만 늘어나게
    //TODO 해당 상품이 진짜 있는 상품이고 수량 확인
    public void addToCart(String sessionId, CartDTO item) {
        List<CartDTO> cartItems = cartItemsBySession.get(sessionId);
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            cartItemsBySession.put(sessionId, cartItems);
        }
        cartItems.add(item);
    }

    public List<CartDTO> getCartItems(String sessionId) {
        if (sessionId == null) {
            throw new NullPointerException("세션 오류");
        }
        List<CartDTO> list = new ArrayList<>();
        return cartItemsBySession.put(sessionId, list);
    }

    public void clearCart(String sessionId) {
        cartItemsBySession.remove(sessionId);
    }

    // Singleton 패턴 적용하는데 해당 CartStorageHolder 클래스를 이용하는 이유
    // 해당 클래스를 통해 인스턴스가 필요한 시점에 생성될 수 있음
    // 처음으로 getInstance() 호출할 떄 해당 CartStorage 생성됨.
    // 단일 인스턴스를 보장하며 CartStorage 클래스의
//    private static class CartStorageHolder {
//        private static final CartStorage INSTANCE = new CartStorage();
//    }
//
//    // 해당 인스턴스를 가져오기 위한 메서드
//    public static CartStorage getInstance() {
//        return CartStorageHolder.INSTANCE;
//    }
}
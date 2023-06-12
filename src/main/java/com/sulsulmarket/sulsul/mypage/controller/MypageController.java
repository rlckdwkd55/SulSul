package com.sulsulmarket.sulsul.mypage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sulsulmarket.sulsul.config.LocalDateTimeSerializer;
import com.sulsulmarket.sulsul.mypage.dto.*;
import com.sulsulmarket.sulsul.mypage.service.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MypageController {

    @Autowired
    private MypageService mypageService;
    private String Order_Detail;

    /**
     * 주문내역
     * 마이페이지 클릭 시 가장 처음 보이는 화면
     * 사용자가 주문 한 내역을 보여준다.
     */
    /*
    네임 룰을 약속한것이 있지? http - > 즉 rest API를 활용할땐, 카멜 케이스로
    Database에 조회할땐, 스네이크 스펠리으로 맞추기로 했지?
     */
    @PostMapping("/myPage/orderList")
    public ResponseEntity<Object> orderList(@RequestBody Map<String, String> requestBody){
        String json = null;
        //TODO 오더리스트를 내려줄때, ORDERS테이블에 있는 ORDER_NO 값을 받아서 ORDER_DETAIL테이블의 모든 값을 ORDERS테이블과 함께 가져와야해
        //TODO 너가 지금 줘야할 데이터는 HashMap, orderInfo : Orders라는 객체, Orders 5개, 각 Orders 하나당 3개의 detail (하루 3개씩 5일동안 주문했을 시)
        //TODO Orders라는 객체가 총 5개가 나오면, 그중 1st에 해당하는 detail을 같이 담아줄 수 있는 능력은 되어야 하지?
        Map<String, Object> resultMap = new HashMap<>();
        if (requestBody != null) {
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("MEMBER_ID", requestBody.get("userId"));
            List<Orders> orderList = mypageService.getOrderList(parameter);
// List<Order_Detail> detailList = mypageService.getOrderDetailList(parameter);
            //TODO List<Detail_Orders> detailList 가저와!!!

            if (orderList.size() > 0) { // 이 말은 구매 한 내역이 있다면과 동일하다.

                //TODO HASHMAP에 HASHMAP 형태가 맞나.. 흠....
                // key OrderList OrderList 5개 나와야하는거야, OrderList, 하나 호환하고, 그 다음으로 key값으로 order_List의 DETAIL_NO를 키로 DETAIL LIST를 반환할거야

                resultMap.put("orderList", orderList);

                log.info("orderList 결과 입니다. : {}", orderList );

                    for(Orders order : orderList) { //요거는 향상된 for문 for(int i = 0; resultList.size() > 0; i++)  orders = resultList.get(i) // 구매 한 내역 수 만큼 반복문수행
                        //5개중에 첫번째 order의 경우
                        //order no가 1,2,3,4,5 이렇게 있다 치자, 1-1, 1-2, 1-3 이면 1을 가져온거지 위에서 order 번호 -1,-2,-3을 가져오고 그걸 detail List담아주면 되지
                        // 그럼 지금 문제가 1,2,3,4,5 detail List 같아 get order_no만 달라지고 뒤로 value 갖고 가는게 같아 지금, 이러면 의미가 없어
                        parameter.put("ORDER_NO", order.getORDER_NO());

                        //얘가 조회해올 데이터는 우리가 키로 잡는 order_no를 where의 조건으로
                        //가져온 리스트를 담아야해
                        List<Order_Detail> detailList = mypageService.getOrderDetailList(parameter); //Order Detail Table에서
                        log.info("detailList 결과 입니다. : {}", detailList );

                        //5번 다 다른 ORDER_NO의 LIST를 반환하지?

                        resultMap.put(String.valueOf(order.getORDER_NO()), detailList); // 구매 한 내역 수 만큼 반복문 수행해서 detailList를 꺼내온다. -> List<Detail_Orders>

                    }

                Gson gson = new GsonBuilder().create();

                json = gson.toJson(resultMap);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);

    }


    /**
     * 상품후기 페이지
     * 사용자가 작성 한 리뷰내역을 보여준다.
     */
    @PostMapping("/myPage/reviewList")
    public ResponseEntity<Object> reviewList(@RequestBody Map<String, String> requestBody){
        String json = null;

        if (requestBody != null) {
            Map<String, String> parameter = new HashMap<>();
            parameter.put("MEMBER_ID", requestBody.get("userId"));
            List<Review> resultList = mypageService.getReviewList(parameter);

            if (resultList.size() > 0) {
                Gson gson = new GsonBuilder().create();

                json = gson.toJson(resultList);

            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);

    }

    /**
     * 회원정보 조회
     * 로그인 한 사용자의 정보를 뿌려준다.
     */
    @PostMapping("/myPage/userInfo")
    public ResponseEntity<Object> userInfo(@RequestBody Map<String, String> requestBody){
        String json = null;

        if (requestBody != null) {
            Map<String, String> parameter = new HashMap<>();
            parameter.put("MEMBER_ID", requestBody.get("userId"));
            List<Member> resultList = mypageService.getUserInfo(parameter);

            if (resultList.size() > 0) {
                Gson gson = new GsonBuilder().create();

                json = gson.toJson(resultList);

            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);

    }

    /**
     * 배송지 리스트
     * ADDRESS 테이블에 저장 된 주소값을
     * 로그인 한 USER_ID 와 동일한 MEMBER_ID 값을 뿌려준다.
     */
    @PostMapping("/myPage/addressList")
    public ResponseEntity<Object> addressList(@RequestBody Map<String, String> requestBody){
        String json = null;

        if (requestBody != null) {
            Map<String, String> parameter = new HashMap<>();
            parameter.put("MEMBER_ID", requestBody.get("userId"));
            List<Address> resultList = mypageService.getAddressList(parameter);

            if (resultList.size() > 0) {
                Gson gson = new GsonBuilder().create();

                json = gson.toJson(resultList);

            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);

    }

    @PostMapping("/myPage/cancelRefundList")
    public ResponseEntity<Object> cancelRefundList(@RequestBody Map<String, String> requestBody){
        String json = null;

        if (requestBody != null) {
            Map<String, String> parameter = new HashMap<>();
            parameter.put("MEMBER_ID", requestBody.get("userId"));
            List<Claim_Info> resultList = mypageService.getCancelRefundList(parameter);
            log.info("[INFO] result List CLAIM NAME -> {}", resultList.get(0).getCLAIM_NAME());

            if (resultList.size() > 0) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
                Gson gson = gsonBuilder.setPrettyPrinting().create();

                json = gson.toJson(resultList);

            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);

    }
}

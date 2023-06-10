package com.sulsulmarket.sulsul.mypage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sulsulmarket.sulsul.mypage.dto.Orders;
import com.sulsulmarket.sulsul.mypage.dto.Review;
import com.sulsulmarket.sulsul.mypage.service.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MypageController {

    @Autowired
    private MypageService mypageService;

    /**
     * 주문내역
     * 마이페이지 클릭 시 가장 처음 보이는 화면
     * 사용자가 주문 한 내역을 보여준다.
     */
    @PostMapping("/myPage/orderList")
    public ResponseEntity<Object> orderList(@RequestBody Map<String, String> requestBody){
        String json = null;

        if (requestBody != null) {
            Map<String, String> parameter = new HashMap<>();
            parameter.put("MEMBER_ID", requestBody.get("userId"));
            List<Orders> resultList = mypageService.getOrderList(parameter);

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
}

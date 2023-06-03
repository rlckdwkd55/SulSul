package com.sulsulmarket.sulsul.mypage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sulsulmarket.sulsul.mypage.dto.Orders;
import com.sulsulmarket.sulsul.mypage.service.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MypageController {

    @Autowired
    private MypageService mypageService;

    @GetMapping("/myPage/orderList")
    public ResponseEntity<Object> orderList(@RequestBody Map<String, Integer> orderListMap){



        return null;

    }

}

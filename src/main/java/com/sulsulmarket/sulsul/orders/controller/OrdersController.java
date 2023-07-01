package com.sulsulmarket.sulsul.orders.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sulsulmarket.sulsul.orders.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import retrofit2.http.POST;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins="*", allowedHeaders = "*")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/orders")
    public ResponseEntity<Object> doOrder(@RequestBody HashMap<String, Object> orderMap){

        if(orderMap != null){
            ordersService.insertOrders(orderMap);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



        return new ResponseEntity<>(HttpStatus.OK);
    }

}

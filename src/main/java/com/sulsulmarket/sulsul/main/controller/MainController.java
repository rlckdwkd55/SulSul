package com.sulsulmarket.sulsul.main.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sulsulmarket.sulsul.main.dto.Product;
import com.sulsulmarket.sulsul.main.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/main")
    public ResponseEntity<Object> main(){

        //TODO 18개의 상품 중 10개는 best, 8개는 새로운 상품을 찾아와서 이 mainResult에 담아주면 되는거겠지
        log.info("Main Controller 호출 됐당 ");
        List<Product> mainResult = mainService.getBestRankingProd();
        log.info("상위 10개 가져온거 찍어보자 main result size: {}", mainResult.size());
        HashMap<String,List<Product>> resultMap =new HashMap<>();
        if(mainResult.size() > 0){
            resultMap.put("bestItems", mainResult);
        }

        List<Product> newProduct = mainService.getNewProduct();
        log.info("새로운거 8개 가져온거 찍어보자 main result size: {}", newProduct.size());
        if(newProduct.size() > 0){
            resultMap.put("newItems", newProduct);
        }


        List<HashMap<String,List<Product>>> result = new ArrayList<>();
        result.add(resultMap);


        log.info("잘 들어갔뉘? result size: {}",  result.size());




        Gson gson = new GsonBuilder().create();

        String json = gson.toJson(result);

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

}

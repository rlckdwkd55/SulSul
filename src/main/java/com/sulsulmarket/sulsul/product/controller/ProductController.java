package com.sulsulmarket.sulsul.product.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.product.service.ProductService;
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
@CrossOrigin(origins="*", allowedHeaders = "*")
@Slf4j
public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping("/product/productDetail")
    public ResponseEntity<Object> productDetail(@RequestBody Map<String, Integer> productDetailMap) { // productDetailMap = Map<Product_no : 1>

        String json = null;
        Gson gson = new GsonBuilder().create();

        int productNo = productDetailMap.get("productNo");

//        log.info("도달값이 뭐니 : {}", productNo);
        if (productNo > 0){
//            log.info("요청하는 이름이 뭐니 :{}",productNo);
            Map<String, Integer> parameter = new HashMap<>();
            parameter.put("PRODUCT_NO", productNo);
            Product result = productService.getProductDetail(parameter);

            json = gson.toJson(result);

        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }


    @PostMapping("/product/categoryList")
    public ResponseEntity<List<Product>> categoryList(@RequestBody Product product){
        try {
        List<Product> categoryList = productService.getCategoryList(product);

        if (categoryList == null){
            return null;
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryList);
        } catch (Exception e){
            log.error("Category Select Fail : {} ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}


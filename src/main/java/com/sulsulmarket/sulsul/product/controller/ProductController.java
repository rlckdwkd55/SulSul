package com.sulsulmarket.sulsul.product.controller;

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

        try{
            Product product = productService.getProductDetail(productDetailMap.get("productNo"));
            log.info("Product Data Get Is Success");
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (Exception e){
            log.error("ProductController Is Get By ProductNo Exception : {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
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


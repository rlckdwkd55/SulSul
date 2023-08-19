package com.sulsulmarket.sulsul.product.service;

import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.product.dao.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductDao productDao;

//    public Product getProductDetail(Integer parameter){
//
//        return productDao.getProductDetail(parameter);
//    }

    public Product getProductDetail(Integer productNo){
        if (productNo <= 0){
            log.info("Product Data Is Error");
            throw new IllegalArgumentException("상품번호는 0보다 큰 값이어야 합니다.");
        }
        Product product = productDao.getProductDetail(productNo);
        if (product == null || Objects.isNull(product)){
            log.error("Product Data Is Null By ProductNo");
            throw new NullPointerException("상품번호로 해당 상품을 찾을 수 없습니다.");
        }
        return product;
    }

    public List<Product> getCategoryList(Product product){

        if (product == null){
            log.error("Not Found Product");
            throw new NullPointerException("상품이 없음");
        }

        return productDao.getCategoryList(product);
    }



}

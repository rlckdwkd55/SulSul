package com.sulsulmarket.sulsul.product.service;

import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.product.dao.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product getProductDetail(Map<String, Integer> parameter){

        return productDao.getProductDetail(parameter);
    }

    public List<Product> getCategoryList(Product product){

        if (product == null){
            log.error("Not Found Product");
            throw new NullPointerException("상품이 없음");
        }

        return productDao.getCategoryList(product);
    }



}

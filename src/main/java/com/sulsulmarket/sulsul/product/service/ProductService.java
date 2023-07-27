package com.sulsulmarket.sulsul.product.service;

import com.sulsulmarket.sulsul.config.DataBaseConfig;
import com.sulsulmarket.sulsul.product.dto.Product;
import com.sulsulmarket.sulsul.product.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product getProductDetail(Map<String, Integer> parameter){

        return productDao.getProductDetail(parameter);
    }
}

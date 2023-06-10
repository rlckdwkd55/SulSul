package com.sulsulmarket.sulsul.product.dao;

import com.sulsulmarket.sulsul.product.dto.Product;
import com.sulsulmarket.sulsul.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductDao implements ProductMapper {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductDetail(Map<String, Integer> parameter) {
        return productMapper.getProductDetail(parameter);
    }

    @Override
    public Product getProductByProductNo(int productNo) {
        return productMapper.getProductByProductNo(productNo);
    }
}

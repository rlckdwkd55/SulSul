package com.sulsulmarket.sulsul.product.dao;

import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.dto.review.Review;
import com.sulsulmarket.sulsul.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao implements ProductMapper {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductDetail(Integer productNo) {
        return productMapper.getProductDetail(productNo);
    }

    @Override
    public Product getProductByProductNo(int productNo) {

        return productMapper.getProductByProductNo(productNo);
    }

    @Override
    public List<Product> getCategoryList(Product product){

        return productMapper.getCategoryList(product);
    }

    @Override
    public List<Product> getAllProductList() {
        return productMapper.getAllProductList();
    }

}

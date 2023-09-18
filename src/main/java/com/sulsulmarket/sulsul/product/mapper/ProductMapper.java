package com.sulsulmarket.sulsul.product.mapper;

import com.sulsulmarket.sulsul.dto.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    Product getProductDetail(Integer productNo);

    Product getProductByProductNo(int productNo);

    List<Product> getCategoryList(Product product);

    List<Product> getAllProductList();

}

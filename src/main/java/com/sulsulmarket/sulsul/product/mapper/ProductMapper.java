package com.sulsulmarket.sulsul.product.mapper;

import com.sulsulmarket.sulsul.product.dto.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    Product getProductDetail(Map<String, Integer> parameter);

    Product getProductByProductNo(int productNo);

    List<Product> getCategoryList(Product product);
}

package com.sulsulmarket.sulsul.main.mapper;

import com.sulsulmarket.sulsul.dto.product.Product;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

import java.util.List;


@Mapper
public interface MainMapper {

    List<Product> getBestRankingProd();

    List<Product> getNewProduct();

    List<String> getProductNameList(String requestString);

    List<Product> getLikeSearchList(Map<String, String> parameter);

    String getLikeSearchListCount(String parameter);

    List<Product> getPagingList(Map<String, String> parameter);
}

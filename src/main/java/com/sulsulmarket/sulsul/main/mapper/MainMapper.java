package com.sulsulmarket.sulsul.main.mapper;

import com.sulsulmarket.sulsul.main.dto.Product;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

import java.util.List;


@Mapper
public interface MainMapper {

    List<Product> getBestRankingProd();

    List<Product> getNewProduct();

    List<String> getLikeList(Map<String, String> parameter);

    List<Product> getLikeSearchList(Map<String, String> parameter);

    String getLikeSearchListCount(Map<String, String> parameter);

    List<Product> getPagingList(Map<String, String> parameter);
}

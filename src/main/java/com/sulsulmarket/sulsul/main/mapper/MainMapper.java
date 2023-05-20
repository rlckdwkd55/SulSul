package com.sulsulmarket.sulsul.main.mapper;

import com.sulsulmarket.sulsul.main.dto.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MainMapper {

    List<Product> getBestRankingProd();

    List<Product> getNewProduct();
}

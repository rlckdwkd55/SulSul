package com.sulsulmarket.sulsul.main.dao;

import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.main.mapper.MainMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class MainDao implements MainMapper {

    @Autowired
    private MainMapper mainMapper;

    @Override
    public List<Product> getBestRankingProd() {
        return mainMapper.getBestRankingProd();
    }

    @Override
    public List<Product> getNewProduct(){
        return mainMapper.getNewProduct();
    }

    @Override
    public List<String> getProductNameList(String requestString){

        return mainMapper.getProductNameList(requestString);
    }

    @Override
    public List<Product> getLikeSearchList(Map<String, String> parameter){
        return mainMapper.getLikeSearchList(parameter);
    }

    @Override
    public String getLikeSearchListCount(String parameter){
        return mainMapper.getLikeSearchListCount(parameter);
    }

    @Override // 재정의 //
    public List<Product> getPagingList(Map<String, String> parameter){
        return mainMapper.getPagingList(parameter);
    }

}

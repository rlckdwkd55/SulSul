package com.sulsulmarket.sulsul.main.dao;

import com.sulsulmarket.sulsul.main.dto.Product;
import com.sulsulmarket.sulsul.main.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
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
    public List<String> getLikeList(Map<String, String> parameter){
        return mainMapper.getLikeList(parameter);
    }

    @Override
    public List<Product> getLikeSearchList(Map<String, String> parameter){
        return mainMapper.getLikeSearchList(parameter);
    }

    @Override
    public String getLikeSearchListCount(Map<String, String> parameter){
        return mainMapper.getLikeSearchListCount(parameter);
    }

    @Override // 재정의 //
    public List<Product> getPagingList(Map<String, String> parameter){
        return mainMapper.getPagingList(parameter);
    }

}

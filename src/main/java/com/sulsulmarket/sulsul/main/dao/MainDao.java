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


}

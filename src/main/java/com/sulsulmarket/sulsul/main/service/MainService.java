package com.sulsulmarket.sulsul.main.service;

import com.sulsulmarket.sulsul.config.DataBaseConfig;
import com.sulsulmarket.sulsul.main.dao.MainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sulsulmarket.sulsul.main.dto.Product;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {

    @Autowired
    private MainDao mainDao;

    @Autowired
    private DataBaseConfig dbConfig;



    public List<Product> getBestRankingProd(){
        //TODO list는 현재 null 이잖아, list에 담을 값은 DataBase에 있으니깐,
        //DataBase에 요청을 해보자
        List<Product> mainResult = mainDao.getBestRankingProd();
        return mainResult;
    }
    public List<Product> getNewProduct(){

        List<Product> mainResult = mainDao.getNewProduct();
        return mainResult;
    }


}

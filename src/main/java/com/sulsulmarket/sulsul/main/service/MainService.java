package com.sulsulmarket.sulsul.main.service;

import com.sulsulmarket.sulsul.Util.SulSulUil;
import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.main.dao.MainDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MainService {

    @Autowired
    private MainDao mainDao;



    public List<Product> getBestRankingProd() throws Exception{
        //TODO list는 현재 null 이잖아, list에 담을 값은 DataBase에 있으니깐,
        //DataBase에 요청을 해보자
        List<Product> bestRankingList = mainDao.getBestRankingProd();

        return bestRankingList;
    }
    public List<Product> getNewProduct() throws Exception{

        List<Product> newProductList = mainDao.getNewProduct();

        return newProductList;
    }

    /**
     * 검색(비동기) 서비스
     */
    public List<String> getProductNameList(Map<String, String> parameter) throws Exception{

        String result = parameter.get("REQUEST_STRING");
//        log.info("파라미터 값이 잘 가고 있늬? value : {} ",result);
        List<String> resultList = mainDao.getProductNameList(parameter);

        return resultList;
    }

//    public List<Product> getLikeSearchList(Map<String, String> parameter){
//        String requestString = parameter.get("REQUESTLIST_STRING");
//        int pageNum = Integer.parseInt(parameter.get("PAGE_NUMBER"));
//        int resultTotalCount = Integer.parseInt(mainDao.getLikeSearchListCount(parameter));
//        Map<String, Integer> page = SulSulUil.getPage(pageNum, resultTotalCount);
//        int startPage = page.get("startNum");
//        int endPage = page.get("endNum");
//        parameter.put("START_PAGE", String.valueOf(startPage));
//        parameter.put("END_PAGE", String.valueOf(endPage));
//
//        if(pageNum == 0){
//            List<Product> resultList = mainDao.getLikeSearchList(parameter);
//        } else if (){
//
//        }
//
//
//        return resultList;
//    }

    public String getLikeSearchListCount(Map<String, String> parameter){
        String resultListCount = mainDao.getLikeSearchListCount(parameter);
        return resultListCount;
    }

    public List<Product> getPagingList(Map<String, String> parameter, int pageNum) throws Exception{

        String requestString = parameter.get("REQUESTLIST_STRING");
        int resultTotalCount = Integer.parseInt(mainDao.getLikeSearchListCount(parameter));
        Map<String, Integer> page = SulSulUil.getPage(pageNum, resultTotalCount);
        int startPage = page.get("startNum");
        int endPage = page.get("endNum");
        parameter.put("START_PAGE", String.valueOf(startPage));
        parameter.put("END_PAGE", String.valueOf(endPage));

        List<Product> pagingList = mainDao.getPagingList(parameter);

        return pagingList;
    }

}

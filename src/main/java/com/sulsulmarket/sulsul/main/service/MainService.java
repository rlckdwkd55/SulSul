package com.sulsulmarket.sulsul.main.service;

import com.sulsulmarket.sulsul.Util.SulSulUtil;
import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.main.dao.MainDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public List<String> getProductNameList(Map<String, String> requestMap) throws Exception{
        List<String> resultList;
        try{
            String requestString = requestMap.get("requestString");
            if(requestString == null){
                log.error("getProductNameList is null, Invalid Request Param");
                return null;
            }
            resultList = mainDao.getProductNameList(requestString);
            return resultList;
        } catch (Exception e){
            log.error("get ProductNameList has been Exception, ",e);
            return null;
        }
    }

    public List<Product> getPagingList(Map<String, String> productListMap) throws Exception{

        Map<String, String> parameter = new HashMap<>();
        List<Product> resultList = null;
        String requestString = productListMap.get("requestString");
        int resultTotalCount = 0;
        if(requestString != null){
            resultTotalCount = Integer.parseInt(mainDao.getLikeSearchListCount(requestString));
            if(resultTotalCount > 0){
                parameter.put("REQUESTLIST_STRING", requestString);
            }
        }
        int pageNum = Integer.parseInt(productListMap.get("page"));
        if(resultTotalCount > 0 && pageNum > 0){
            Map<String, Integer> page = SulSulUtil.getPage(pageNum, resultTotalCount);
            parameter.put("START_PAGE", String.valueOf(page.get("startNum")));
            parameter.put("END_PAGE", String.valueOf(page.get("endNum")));
            resultList = mainDao.getPagingList(parameter);
            log.debug("getPaging Product Result List : {}", resultList);
        } else{
            log.warn("Product Search List is null, requestString : {}, resultTotalCount : {}, pageNum : {}",
                    requestString, resultTotalCount, pageNum);
            return null;
        }
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

//    public String getLikeSearchListCount(Map<String, String> parameter){
//        String resultListCount = mainDao.getLikeSearchListCount(parameter);
//        return resultListCount;
//    }
}

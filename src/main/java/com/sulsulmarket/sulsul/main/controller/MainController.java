package com.sulsulmarket.sulsul.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sulsulmarket.sulsul.main.dto.Product;
import com.sulsulmarket.sulsul.main.service.MainService;
import jdk.jshell.spi.ExecutionControlProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//컨트롤러입니다
@Controller
@Slf4j
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/main")
    public ResponseEntity<Object> main() {

        //TODO 18개의 상품 중 10개는 best, 8개는 새로운 상품을 찾아와서 이 mainResult에 담아주면 되는거겠지
        // 리스트 없애고 해시맵만 던져줄것
        log.info("Main Controller 호출 됐당 ");
        List<Product> mainResult = mainService.getBestRankingProd();
        log.debug("상위 10개 가져온거 찍어보자 main result size: {}", mainResult.size());
        HashMap<String, List<Product>> resultMap = new HashMap<>();
        if (mainResult.size() > 0) {
            resultMap.put("bestItems", mainResult);
        }

        List<Product> newProduct = mainService.getNewProduct();
        log.debug("새로운거 8개 가져온거 찍어보자 main result size: {}", newProduct.size());
        if (newProduct.size() > 0) {
            resultMap.put("newItems", newProduct);
        }


//        List<HashMap<String,List<Product>>> result = new ArrayList<>();
//        result.add(resultMap);


//        log.info("잘 들어갔뉘? result size: {}",  result.size());


        Gson gson = new GsonBuilder().create();

        String json = gson.toJson(resultMap);

        return new ResponseEntity<>(json, HttpStatus.OK);


    }

    /**
     * 검색창에 특정 키워드를 입력하면
     * 그 키워드와 연관된 product_List(상품명)을 뿌려준다
     */
    @PostMapping("/product/productNameList")
    public ResponseEntity<Object> productNameList(@RequestBody String requestString) {
        String json = null;
        if (requestString != null) {
            // SearchString이 null이 아니라면, 검색을 진행한다.
            // DB에서 가지고 오는 결과를 List로 담고, json으로 파싱하여 돌려준다.
            // sql과 연동할 때 Map을 많이쓴다.
            Map<String, String> parameter = new HashMap<>();
            parameter.put("REQUEST_STRING", requestString);
            List<String> resultList = mainService.getLikeList(parameter);

            if (resultList.size() > 0) {
                Gson gson = new GsonBuilder().create();

                json = gson.toJson(resultList);

            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    /**
     * 상품검색(비동기)에서 키워드 입력 후 검색을 누르면
     * 해당 검색에 해당하는 제품을 보여주는 제품상세페이지를 띄워준다.
     */
    @PostMapping("/product/productList")
    public ResponseEntity<Object> productList(@RequestBody String requestString, String page) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            String requestListString = objectMapper.writeValueAsString(requestString);
            String pageNum = objectMapper.writeValueAsString(page);


        log.info("요기 도달하는 리퀘스트 이름은 뭐니 : {}", requestListString);
        if (requestListString != null && pageNum == null) {
            log.info("요청하는 이름이 뭐니 :{}",requestListString);
            Map<String, String> parameter = new HashMap<>();
            parameter.put("REQUESTLIST_STRING", requestListString);
            List<Product> resultList = mainService.getLikeSearchList(parameter);
            String totalCount = mainService.getLikeSearchListCount(parameter);
            //TODO 첫 페이지 이기 때문에, 총량 COUNT(*) 갯수를 파악하고
            log.info("리스트 사이즈가 몇이니 : {}, 총 갯수가 몇개니 : {}", resultList.size(), totalCount);
            if (resultList.size() > 0) {
                //TODO map을 만들어서 key, value로 먼저 리스트, 그리고 총량 totalCount, db에서 리턴받은 갯수 이런식으로 넣어서
                // json으로 형변환
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("requestList", resultList);
                resultMap.put("totalCount", totalCount);
                Gson gson = new GsonBuilder().create();
                json = gson.toJson(resultMap);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        } catch (Exception e){

        }
        return new ResponseEntity<>(json, HttpStatus.OK);


    }




}

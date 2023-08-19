package com.sulsulmarket.sulsul.main.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sulsulmarket.sulsul.dto.product.Product;
import com.sulsulmarket.sulsul.main.service.MainService;
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
@CrossOrigin(origins="*", allowedHeaders = "*")
@Slf4j
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/main")
    public ResponseEntity<Object> main() {
        Gson gson = new GsonBuilder().create();
        HashMap<String, List<Product>> resultMap = new HashMap<>();
        try {
            List<Product> bestItemList = mainService.getBestRankingProd();
            List<Product> newProductList = mainService.getNewProduct();
            if(bestItemList.size()> 0){
                resultMap.put("bestItems",bestItemList  );
            }else if (newProductList.size()> 0){
                resultMap.put("newItems", newProductList);
            }

            String json = gson.toJson(resultMap);
            return new ResponseEntity<>(json, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Main Service has been Exception : ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 검색창에 특정 키워드를 입력하면
     * 그 키워드와 연관된 product_List(상품명)을 뿌려준다
     */
    @PostMapping("/product/productNameList")
    public ResponseEntity<Object> productNameList(@RequestBody Map<String, String> requestMap) {

        String json = null;
        Gson gson = new GsonBuilder().create();
        try {
            if(requestMap.isEmpty()){
                log.warn("ProductNameList, Request Param is Empty");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            List<String> resultList = mainService.getProductNameList(requestMap);
            if(resultList.size() > 0){
                json = gson.toJson(resultList);
            }
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            log.error("ProductNameList has been Exception :", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 상품검색(비동기)에서 키워드 입력 후 검색을 누르면
     * 해당 검색에 해당하는 제품을 보여주는 제품목록페이지를 띄워준다.
     */
    @PostMapping("/product/productList")
    public ResponseEntity<Object> productList(@RequestBody Map<String, String> productListMap) {
        String json = null;
        Gson gson = new GsonBuilder().create();
        try{
            if(productListMap.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.OK);
            }

            List<Product> resultList = mainService.getPagingList(productListMap);
            log.warn("result List Size : {}", resultList.size());
            if(resultList.size() > 0){
                json = gson.toJson(resultList);
            }

        } catch (NullPointerException npe){
            log.error("Product Search List has been Null Point Exception, resultList");
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (Exception e){
            log.error("Product Search List has been Exception : ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }


    //    @GetMapping("/main")
//    public ResponseEntity<Object> main() {
//
//        //TODO 18개의 상품 중 10개는 best, 8개는 새로운 상품을 찾아와서 이 mainResult에 담아주면 되는거겠지
//        // 리스트 없애고 해시맵만 던져줄것
//        log.info("Main Controller 호출 됐당 ");
//        List<Product> mainResult = mainService.getBestRankingProd();
//        log.debug("상위 10개 가져온거 찍어보자 main result size: {}", mainResult.size());
//        HashMap<String, List<Product>> resultMap = new HashMap<>();
//        if (mainResult.size() > 0) {
//            resultMap.put("bestItems", mainResult);
//        }
//
//        List<Product> newProduct = mainService.getNewProduct();
//        log.debug("새로운거 8개 가져온거 찍어보자 main result size: {}", newProduct.size());
//        if (newProduct.size() > 0) {
//            resultMap.put("newItems", newProduct);
//        }
//
//
////        List<HashMap<String,List<Product>>> result = new ArrayList<>();
////        result.add(resultMap);
//
//
////        log.info("잘 들어갔뉘? result size: {}",  result.size());
//
//
//        Gson gson = new GsonBuilder().create();
//
//        String json = gson.toJson(resultMap);
//
//        //String json = "result";
//        return new ResponseEntity<>(json.getBytes(), HttpStatus.OK);
//
//
//    }


    //        if (requestListString != null && pageNum == 1) {
//            log.info("요청하는 이름이 뭐니 :{}",requestListString);
//            parameter.put("REQUESTLIST_STRING", requestListString);
//            List<Product> resultList = mainService.getLikeSearchList(parameter);
//            String totalCount = mainService.getLikeSearchListCount(parameter);
//            //TODO 첫 페이지 이기 때문에, 총량 COUNT(*) 갯수를 파악하고
//            log.info("리스트 사이즈가 몇이니 : {}, 총 갯수가 몇개니 : {}", resultList.size(), totalCount);
//            if (resultList.size() > 0) {
//                //TODO map을 만들어서 key, value로 먼저 리스트, 그리고 총량 totalCount, db에서 리턴받은 갯수 이런식으로 넣어서
//                // json으로 형변환
//                Map<String, Object> resultMap = new HashMap<>();
//                resultMap.put("requestList", resultList);
//                resultMap.put("totalCount", totalCount);
//                json = gson.toJson(resultMap);
//            }
//        } else if (pageNum > 1){
    //TODO pageNum 이 0보다 큰 경우 ex) 1,2,3,4,5,6,7...의 경우 page 값을 쿼리문에 담아갈 필요가 있다.
//            parameter.put("REQUESTLIST_STRING", requestListString); // 검색을 할 단어 (제)
//            String totalCount = mainService.getLikeSearchListCount(parameter); // 제라는 단어로 검색해서 나온 총 갯수
//            log.info("요청 하는 값을 찍어보자 : {}, : {}", pageNum, Integer.parseInt((totalCount)));
//            Map<String, Integer> page = SulSulUil.getPage(pageNum, Integer.parseInt((totalCount)));
//            int startPage = page.get("startNum");
//            int endPage = page.get("endNum");
//            parameter.put("START_PAGE", String.valueOf(startPage)); //PK가 PRIMARY KEY값을 기준으로 쿼리문 WHERE절 조건 START_PAGE <= PRIMARY KEY >= END_PAGE
//            parameter.put("END_PAGE", String.valueOf(endPage));
    //TODO 제, 2 라는 값으로 요청을 하였는데, 요청이 수행되지 않고, 아무것도 없는 이유가 뭐냐면
    //일단, 페이지 넘버가 1이 아닌 경우는 검색창에 나온 상품의 1페이지를 보고 사용자가 2페이지를 누른 경우,
    //그러면 너가 리턴을 해줘야하는 값은, 전체 검색이 아니라, 2페이지의 상품들을 보여줘야하잖아
    //지금 그게 구현되어 있지 않잖아 JSON을 가져온다해서 해결될게 아니고 JSON에 값을 담을 어떤 동작을 구현해야하는거지
    //10개가 상품이라는 객체를 10개를 담아서 프론트로 보내줘야해

//            log.info("시작 페이지 : {}, 조건 절에 들어갈 페이지 : {}", startPage, endPage);

//            List<Product> pagingList = mainService.getPagingList(parameter);
//            log.info("pagingList 값을 찍어 보자 : {}" , pagingList.size());
//            //이미 페이지 생각해서 데이터 말아서 리스트로 받아왔는데? 그냥 전달만 하면 되잖아?!
//            json = gson.toJson(pagingList);

//        }else {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }


//    @PostMapping("/product/productNameList")
//    public ResponseEntity<Object> productNameList(@RequestBody Map<String, String> searchMap) {
//
//        String json = null;
//        Gson gson = new GsonBuilder().create();
//
//        String requestString = null;
//        try {
//            if(searchMap.isEmpty()){
//                log.warn("ProductNameList, Request Param is Empty");
//                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//            }
//        List<String> resultList = mainService.getProductNameList(searchMap);
//            requestString = searchMap.get("requestString");
//            if (requestString != null) {
//                // SearchString이 null이 아니라면, 검색을 진행한다.
//                // DB에서 가지고 오는 결과를 List로 담고, json으로 파싱하여 돌려준다.
//                // sql과 연동할 때 Map을 많이쓴다.
//                Map<String, String> parameter = new HashMap<>();
//                parameter.put("REQUEST_STRING", requestString);
//
////                log.info("왜 아무것도 못가져와", resultList  );
//                json = gson.toJson(resultList);
//
////                if (resultList != null) {
////                    // resultList의 결과값이 있어야만 json data를 파싱한다.
////                    Gson gson = new GsonBuilder().create();
////
////                    json = gson.toJson(resultList);
////
////                } else{
////                    log.error("productNameList Controller result List is NULL: {}", resultList);
////                    //서버 입장에서 request body값을 갖고 리스트를 조회하였는데, 데이터가 없는 경우는
////                    //사용자가 검색요청한 결과값이 정말 없을 경우 이기 때문에, JSON은 NULL 서버는 OK의 응답을 준다.
////                    return new ResponseEntity<>(null, HttpStatus.OK);
////                }
//
//            } else {
//                log.error("productNameList RequestString is NULL");
//                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            log.error("ProductNameList has been Exception :", e);
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>(json, HttpStatus.OK);
//    }

}

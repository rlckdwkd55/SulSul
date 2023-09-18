package com.sulsulmarket.sulsul.qna.controller;

import com.sulsulmarket.sulsul.dto.qna.Qna;
import com.sulsulmarket.sulsul.qna.service.QnaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(
        value = "*",
        origins = "*",
        allowedHeaders = "*"
)
public class QnaController {

    @Autowired
    private QnaService qnaService;

    @PostMapping("/api/qna/write")
    public ResponseEntity<Object> qnaWriteHandler(@RequestBody Qna qna) {

        try {
            qnaService.qnaWrite(qna);
            return ResponseEntity.status(HttpStatus.OK).body("문의 등록에 성공하였습니다.");
        } catch (Exception e) {
            log.error("QnaController Is Write Exception ! ! ! : {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/api/qna/get/qnaNo")
    // url 작성 예시 http://localhsot:8080/api/qna/get/qnaNo 지금 요청 URL인데
    // 제가 이제 RequestBody 를 이용해서 parameter 를  받아와서
    // url은 저기고 해당 값은 Body 를 통해서 가져오는 겁니다.
    // 제꺼 노션에서 해당 정리한 글이 있는데 GET / POST 한 번 읽어보시고 예시도 있어서 해보시면 뭐가 다른지 알 것 같아요
    // @01.09 Spring Boot Controller, GET API 정리
    //
//    [Spring Boot Controller](https://www.notion.so/Spring-Boot-Controller-b5398272b89b45de83dfc5f3886e4172?pvs=21)
    //
    //@01.18 Spring Boot Controller, POST API 정리
    //
    //[Spring Boot Post API](https://www.notion.so/Spring-Boot-Post-API-e967b3c04c7246818fe3895dd2440d62?pvs=21)
    //TODO @RequestParam 또는 @PathVariable 이거 두개가 URL ? = test 이런 식으로 쓰는 거에요 GET 방식 사용할 떄 보통 많이 써
    public ResponseEntity<Object> getQnaByQnaNo(@RequestBody Map<String, Integer> parameter) {

        try {
            Qna qnaDTO = qnaService.getQnaByQnaNo(parameter.get("qnaNo"));
            log.info("Qna Data Get Is Success");
            return ResponseEntity.status(HttpStatus.OK).body(qnaDTO);
        } catch (Exception e) {
            log.error("QnaController Is Get By QnaNo Exception : {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/qna/update")
    public ResponseEntity<Object> qnaUpdate(@RequestBody Qna qnaDTO) {

        try {
            qnaService.qnaUpdate(qnaDTO);
            log.info("Qna Data Update Is Success");
            return ResponseEntity.status(HttpStatus.OK).body("상품 문의를 수정하였습니다.");
        } catch (Exception e) {
            log.error("QnaController Update Is Exception : {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/qna/update/status")
    public ResponseEntity<Object> qnaUpdateStatus(@RequestBody Map<String, Integer> parameter) {

        try {
            qnaService.qnaStatusUpdate(parameter.get("qnaNo"));
            log.info("Qna Status Is Delete Update Success");
            return ResponseEntity.status(HttpStatus.OK).body("상품 문의 글을 삭제 하였습니다.");
        } catch (Exception e) {
            log.error("Qna Status Update Is Exception : {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

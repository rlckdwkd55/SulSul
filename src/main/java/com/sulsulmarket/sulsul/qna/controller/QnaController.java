package com.sulsulmarket.sulsul.qna.controller;

import com.sulsulmarket.sulsul.qna.dto.QnaDTO;
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
    public ResponseEntity<Object> qnaWriteHandler(@RequestBody QnaDTO qnaDTO) {

        try {
            qnaService.qnaWrite(qnaDTO);
            log.info("Qna Write Is Success");
            return ResponseEntity.status(HttpStatus.OK).body("문의 등록에 성공하였습니다.");
        } catch (Exception e) {
            log.error("QnaController Is Write Exception ! ! ! : {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/api/qna/get/qnaNo")
    public ResponseEntity<Object> getQnaByQnaNo(@RequestBody Map<String, Integer> parameter) {

        try {
            QnaDTO qnaDTO = qnaService.getQnaByQnaNo(parameter.get("qnaNo"));
            log.info("Qna Data Get Is Success");
            return ResponseEntity.status(HttpStatus.OK).body(qnaDTO);
        } catch (Exception e) {
            log.error("QnaController Is Get By QnaNo Exception : {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/qna/update")
    public ResponseEntity<Object> qnaUpdate(@RequestBody QnaDTO qnaDTO) {

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

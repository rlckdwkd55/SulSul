package com.sulsulmarket.sulsul.payment.controller;

import com.sulsulmarket.sulsul.payment.dto.KakaoApproveResponse;
import com.sulsulmarket.sulsul.payment.dto.KakaoCancelResponse;
import com.sulsulmarket.sulsul.payment.dto.KakaoReadyResponse;
import com.sulsulmarket.sulsul.payment.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins="*", allowedHeaders = "*")
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public ResponseEntity<Object> readyToKakaoPay(@RequestBody Map<String, Object> requestBody) {

        int productNo = (int) requestBody.get("productNo");
        int quantity = (int) requestBody.get("quantity");

        KakaoReadyResponse response = kakaoPayService.kakaoReady(productNo, quantity);

        // tid, redirect url, create-at
        // pg-token, tid를 받아서
        // 결제 승인

        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 결제 성공
     */
    @GetMapping("/success") //"tid":"123145123"
    public ResponseEntity afterPayRequest(@RequestParam("pgToken") String pgToken, @RequestParam("tid") int tid) {
        //TODO null 검증하고 넘기기
        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken, tid);

        if(kakaoApprove == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    /**
     * 환불
     */
    @PostMapping("/refund")
    public ResponseEntity refund() {

        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel();

        if(kakaoCancelResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public void cancel() {

    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() throws Exception {

    }
}

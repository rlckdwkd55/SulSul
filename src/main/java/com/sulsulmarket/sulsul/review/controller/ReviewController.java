package com.sulsulmarket.sulsul.review.controller;

import com.sulsulmarket.sulsul.review.dto.ReviewDTO;
import com.sulsulmarket.sulsul.review.dto.ReviewOrderByMemberId;
import com.sulsulmarket.sulsul.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/api/review/write")
    public ResponseEntity<Object> writeReview(@RequestBody ReviewDTO reviewDTO) {

        log.info("REVIEW_DTO -> {}", reviewDTO.getREVIEW_NO());
        log.info("REVIEW_DTO -> {}", reviewDTO.getREVIEW_CONTENT());
        reviewService.writeReview(reviewDTO);

        return ResponseEntity.status(HttpStatus.OK).body("리뷰 작성 성공");
    }

    @GetMapping("/api/review/list/get")
    public ResponseEntity<Object> getReviewListAll() {

        try {
            List<ReviewDTO> reviewList = reviewService.getReviewListAll();
            return ResponseEntity.status(HttpStatus.OK).body(reviewList);
        } catch (Exception e) {
            log.error("Review List Is Null ! ! !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 리스트 가져오는 것에 실패하였습니다.");
        }
    }

    @PostMapping("/api/review/list/get")
    public ResponseEntity<Object> getReviewListAllByMemberId(@RequestBody Map<String, String> body) {

        try {
            List<ReviewOrderByMemberId> reviewList = reviewService.getReviewListAllByMemberId(body.get("memberId"));
            return ResponseEntity.status(HttpStatus.OK).body(reviewList);
        } catch (Exception e) {
            log.error("ERROR ! ! !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
}
package com.sulsulmarket.sulsul.review.controller;

import com.sulsulmarket.sulsul.dto.review.Review;
import com.sulsulmarket.sulsul.dto.review.ReviewOrderByMemberId;
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
    public ResponseEntity<Object> writeReview(@RequestBody Review review) {

        try {
            reviewService.writeReview(review);
            return ResponseEntity.status(HttpStatus.OK).body("리뷰 작성 성공");
        } catch (Exception e) {
            log.error("error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/api/review/list/get")
    public ResponseEntity<Object> getReviewListAll() {

        try {
            List<Review> reviewList = reviewService.getReviewListAll();
            return ResponseEntity.status(HttpStatus.OK).body(reviewList);
        } catch (Exception e) {
            log.error("Review List Is Null ! ! !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/api/review/list/get")
    public ResponseEntity<Object> getReviewListAllByMemberId(@RequestBody Map<String, String> body) {

        try {
            List<ReviewOrderByMemberId> reviewList = reviewService.getReviewListAllByMemberId(body.get("memberId"));
            return ResponseEntity.status(HttpStatus.OK).body(reviewList);
        } catch (Exception e) {
            log.error("ERROR ! ! !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/api/review/update")
    public ResponseEntity<Object> reviewUpdateByMemberId(@RequestBody Review review) {

        try {
            reviewService.updateReviewByMemberId(review);
            return ResponseEntity.status(HttpStatus.OK).body("수정 성공");
        } catch (Exception e) {
            log.error("Update Is Fail ! ! !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/review/delete")
    public ResponseEntity<Object> reviewDelete(@RequestBody Review review) {
        try {
            reviewService.deleteReviewByMemberId(review.getMEMBER_ID(), review.getDETAIL_NO());
            return ResponseEntity.status(HttpStatus.OK).body("삭제 성공");
        } catch (Exception e) {
            log.error("Delete Is Fail ! ! !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
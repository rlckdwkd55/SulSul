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
            return ResponseEntity.status(HttpStatus.OK).body("등록이 완료 되었습니다.");
        } catch (Exception e) {
            log.error("Review Write Exception..", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/review/list/get")
    public ResponseEntity<Object> getReviewListAllByMemberId(@RequestBody Map<String, String> body) {

        try {
            List<ReviewOrderByMemberId> reviewList = reviewService.getReviewListAllByMemberId(body.get("email"));
            return ResponseEntity.status(HttpStatus.OK).body(reviewList);
        } catch (Exception e) {
            log.error("Review List Get Exception.", e);
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
    public ResponseEntity<Object> reviewDelete(@RequestBody Map<String, Integer> param) {
        try {
            reviewService.deleteReviewByReviewNo(param.get("reviewNo"));
            return ResponseEntity.status(HttpStatus.OK).body("삭제 성공");
        } catch (Exception e) {
            log.error("Delete Is Fail ! ! !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
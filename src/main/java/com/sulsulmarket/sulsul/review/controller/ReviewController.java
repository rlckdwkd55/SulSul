package com.sulsulmarket.sulsul.review.controller;

import com.sulsulmarket.sulsul.review.dto.ReviewDTO;
import com.sulsulmarket.sulsul.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}

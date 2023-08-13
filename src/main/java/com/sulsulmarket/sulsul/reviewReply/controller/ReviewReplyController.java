package com.sulsulmarket.sulsul.reviewReply.controller;

import com.sulsulmarket.sulsul.dto.reviewReply.ReviewReply;
import com.sulsulmarket.sulsul.reviewReply.service.ReviewReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class ReviewReplyController {

    @Autowired
    private ReviewReplyService reviewReplyService;

    @PostMapping("/reply/add")
    public ResponseEntity<Object> reviewReplyAdd(@RequestBody ReviewReply reviewReply) {

        try {
            reviewReplyService.reviewReplyInsert(reviewReply);
            return ResponseEntity.status(HttpStatus.OK).body("리뷰 답글 달기에 성공했습니다.");
        } catch (Exception e) {
            log.error("Review Reply Controller Exception : {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/reply/get")
    public ResponseEntity<Object> getReviewReplyList(@RequestParam int reviewNo) {

        try {
            List<ReviewReply> reviewReplyList = reviewReplyService.getReviewReplyListByReviewNo(reviewNo);
            log.info("Get Review Reply List Is Success");
            return ResponseEntity.status(HttpStatus.OK).body(reviewReplyList);
        } catch (Exception e) {
            log.error("Review Reply Get Controller Exception : {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

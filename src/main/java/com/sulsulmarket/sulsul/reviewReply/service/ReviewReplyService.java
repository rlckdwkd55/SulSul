package com.sulsulmarket.sulsul.reviewReply.service;

import com.sulsulmarket.sulsul.member.dao.MemberDao;
import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import com.sulsulmarket.sulsul.review.dao.ReviewDao;
import com.sulsulmarket.sulsul.review.dto.ReviewDTO;
import com.sulsulmarket.sulsul.reviewReply.dao.ReviewReplyDao;
import com.sulsulmarket.sulsul.reviewReply.dto.ReviewReplyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
@Transactional
public class ReviewReplyService {

    @Autowired
    private ReviewReplyDao reviewReplyDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private ReviewDao reviewDao;

    @Transactional
    public void reviewReplyInsert(ReviewReplyDTO reviewReplyDTO) {

        if (reviewReplyDTO == null || Objects.isNull(reviewReplyDTO)) {
            log.error("Review Reply Content Is Null ! ! !");
            throw new NullPointerException("리뷰 답글에 대한 내용이 없습니다.");
        }

        MemberDTO member = memberDao.getMemberById(reviewReplyDTO.getRESPONDER_ID());

        if (member == null || Objects.isNull(member)) {
            log.error("Not Found Member By Member Id");
            throw new NullPointerException("리뷰 답글을 적은 회원 정보를 찾을 수가 없습니다.");
        }

        ReviewDTO reviewDTO = reviewDao.getReviewByReviewNo(reviewReplyDTO.getREVIEW_NO());

        if(reviewDTO == null || Objects.isNull(reviewDTO)) {
            log.error("Not Found Review By Review No..?");
            throw new NullPointerException("답글을 적을 리뷰 글을 찾을 수가 없습니다.");
        }

        //TODO 리뷰 답글 작성 필요
    }
}

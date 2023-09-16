package com.sulsulmarket.sulsul.reviewReply.service;

import com.sulsulmarket.sulsul.Util.SulSulUtil;
import com.sulsulmarket.sulsul.dto.reviewReply.ReviewReply;
import com.sulsulmarket.sulsul.dto.review.Review;
import com.sulsulmarket.sulsul.member.dao.MemberDao;
import com.sulsulmarket.sulsul.review.dao.ReviewDao;
import com.sulsulmarket.sulsul.reviewReply.dao.ReviewReplyDao;
import com.sulsulmarket.sulsul.dto.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public void reviewReplyInsert(ReviewReply reviewReply) {

        if (reviewReply == null || Objects.isNull(reviewReply)) {
            log.error("Review Reply Content Is Null ! ! !");
            throw new NullPointerException("리뷰 답글에 대한 내용이 없습니다.");
        }

        Member member = memberDao.getMemberByEmail(reviewReply.getRESPONDER_ID());

        if (member == null || Objects.isNull(member)) {
            log.error("Not Found Member By Member Id");
            throw new NullPointerException("리뷰 답글을 적은 회원 정보를 찾을 수가 없습니다.");
        }

        Review review = reviewDao.getReviewByReviewNo(reviewReply.getREVIEW_NO());

        if(review == null || Objects.isNull(review)) {
            log.error("Not Found Review By Review No..?");
            throw new NullPointerException("답글을 적을 리뷰 글을 찾을 수가 없습니다.");
        }

        if(reviewReply.getREPLY_CONTENT() == null || reviewReply.getREPLY_CONTENT().length() == 0 || reviewReply.getREPLY_CONTENT().equals("")) {
            log.warn("Review Reply Contents Is Null ! ! !");
            throw new IllegalArgumentException("리뷰 내용이 없습니다.");
        }

        try {
            reviewReply.setREPLY_ID(SulSulUtil.getNextSequence().intValue());
            log.info("Review Reply :: [{}]", reviewReply);
            reviewReplyDao.reviewReplyAdd(reviewReply);
            log.info("Review Reply Insert Is Success ! ! !");
        } catch (Exception e) {
            log.error("Review Reply Insert Exception : {}", e);
        }
    }

    public List<ReviewReply> getReviewReplyListByReviewNo(int reviewNo) {

        if(reviewNo <= 0) {
            log.error("ReviewNo [{}] < 0 Illegal Review No ! ! !");
            throw new IllegalArgumentException("잘못된 리뷰 번호입니다.");
        }

        List<ReviewReply> reviewReplyList = reviewReplyDao.getReviewReply(reviewNo);

        if (reviewReplyList == null || Objects.isNull(reviewReplyList)) {
            log.error("Get Review Reply List Is Null ! ! !");
            throw new NullPointerException("리뷰 번호로 조회시 가져올 리뷰 리스트가 없습니디.");
        } else {
            log.info("Get Review Reply List By ReviewNo Is Success ! ! !");
            return reviewReplyList;
        }
    }
}
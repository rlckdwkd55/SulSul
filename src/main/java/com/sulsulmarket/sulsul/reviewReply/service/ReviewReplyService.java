package com.sulsulmarket.sulsul.reviewReply.service;

import com.sulsulmarket.sulsul.Util.SulSulUil;
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

        if(reviewReplyDTO.getREPLY_CONTENT() == null || reviewReplyDTO.getREPLY_CONTENT().length() == 0 || reviewReplyDTO.getREPLY_CONTENT().equals("")) {
            log.warn("Review Reply Contents Is Null ! ! !");
            throw new IllegalArgumentException("리뷰 내용이 없습니다.");
        }

        try {
            reviewReplyDTO.setREPLY_ID(Math.toIntExact(SulSulUil.getNextSequence()));
            log.info("ReviewReplyDTO ===>>> [{}]", reviewReplyDTO.toString());
            reviewReplyDao.reviewReplyAdd(reviewReplyDTO);
            log.info("Review Reply Insert Is Success ! ! !");
        } catch (Exception e) {
            log.error("Review Reply Insert Exception : {}", e);
        }
    }

    public List<ReviewReplyDTO> getReviewReplyListByReviewNo(int reviewNo) {

        if(reviewNo <= 0) {
            log.error("ReviewNo [{}] < 0 Illegal Review No ! ! !");
            throw new IllegalArgumentException("잘못된 리뷰 번호입니다.");
        }

        List<ReviewReplyDTO> reviewReplyList = reviewReplyDao.getReviewReply(reviewNo);

        if (reviewReplyList == null || Objects.isNull(reviewReplyList)) {
            log.error("Get Review Reply List Is Null ! ! !");
            throw new NullPointerException("리뷰 번호로 조회시 가져올 리뷰 리스트가 없습니디.");
        } else {
            log.info("Get Review Reply List By ReviewNo Is Success ! ! !");
            return reviewReplyList;
        }
    }
}
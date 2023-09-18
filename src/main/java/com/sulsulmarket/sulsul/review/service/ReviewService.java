package com.sulsulmarket.sulsul.review.service;

import com.sulsulmarket.sulsul.Util.SulSulUtil;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.dto.order.OrderDetail;
import com.sulsulmarket.sulsul.dto.review.Review;
import com.sulsulmarket.sulsul.dto.review.ReviewOrderByMemberId;
import com.sulsulmarket.sulsul.member.dao.MemberDao;
import com.sulsulmarket.sulsul.review.dao.ReviewDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private MemberDao memberDao;

    @Transactional
    public void writeReview(Review review) {

        if(review == null || Objects.isNull(review)) {
            log.error("REVIEW DTO IS NULL ! ! !");
            throw new NullPointerException("데이터가 없습니다.");
        }

        Member member = memberDao.getMemberByEmail(review.getMEMBER_EMAIL());
        if (member == null || Objects.isNull(member)) {
            log.error("Not Found Member Review Insert Fail ! ! !");
            throw new NullPointerException("회원 정보가 없습니다.");
        }

        OrderDetail orderDetail = reviewDao.getOrderDetailList(review.getDETAIL_NO());
        if(orderDetail == null || Objects.isNull(orderDetail)) {
            log.error("Not Found OrderDetail Select Fail ! ! !");
            throw new NullPointerException("주문 상세 번호가 없습니다.");
        }

        if(review.getREVIEW_CONTENT() == null || review.getREVIEW_CONTENT().length() == 0 || review.getREVIEW_CONTENT().equals("")) {
            log.error("Review Content Is Null Insert Fail ! ! !");
            throw new NullPointerException("리뷰 작성 내용이 없습니다.");
        }

        review.setREVIEW_NO(SulSulUtil.getNextSequence().intValue());
        log.info("Review write Success email :: [{}], DetailNo :: [{}]", review.getMEMBER_EMAIL(), review.getDETAIL_NO());
        log.debug("Review Insert Success... ! ! ! ===>>> [{}]", review);
        reviewDao.writeReview(review);
    }


    public List<ReviewOrderByMemberId> getReviewListAllByMemberId(String email) {

        List<ReviewOrderByMemberId> reviewOrderByMemberIdList = reviewDao.getReviewListAllByEmail(email);

        if (reviewOrderByMemberIdList == null || reviewOrderByMemberIdList.isEmpty()) {
            throw new NullPointerException("가져올 리뷰 리스트가 없다");
        }
        log.info("Review Detail  ! !!  !===>>> [{}]", reviewOrderByMemberIdList);

        return reviewOrderByMemberIdList;
    }

    @Transactional
    public void updateReviewByMemberId(Review review) {

        if (review == null || Objects.isNull(review)) {
            log.error("Review Data Is Null ! ! !");
            throw new NullPointerException("Not Found Review Data Is Null");
        }

        int insertCount = reviewDao.updateReviewByMemberId(review);
        if (insertCount <= 0) {
            log.error("Review Update Fail Value Check ! ! !");
            throw new IllegalArgumentException("Value Check Update Fail ! ! !");
        }
    }

    @Transactional
    public void deleteReviewByReviewNo(int reviewNo) {

        if(reviewNo <= 0) {
            log.error("Review No Is IllegalData");
            throw new IllegalArgumentException("Review No is IllegalData");
        }

        reviewDao.deleteReviewByReviewNo(reviewNo);
    }
}

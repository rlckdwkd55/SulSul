package com.sulsulmarket.sulsul.review.service;

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

        if(review.getREVIEW_CONTENT() == null || review.getREVIEW_CONTENT().length() <= 0 || review.getREVIEW_CONTENT() == "") {
            log.error("Review Content Is Null Insert Fail ! ! !");
            throw new NullPointerException("리뷰 작성 내용이 없습니다.");
        }

        log.info("Review Insert Success ! ! ! ===>>> [{}]", review.toString());
        reviewDao.writeReview(review);
    }

    /**
     * 모든 리뷰 리스트를 가져오는 메서드
     */
    public List<Review> getReviewListAll() {

        List<Review> reviewList = reviewDao.getReviewListAllByOrderDetail();

        if(reviewList == null || reviewList.isEmpty()) {
            throw new NullPointerException("가져올 리뷰 리스트가 없습니다.");
        }
        log.info("ReviewDetail ===>>> [{}]", reviewList.toString());

        return reviewList;
    }

    public List<ReviewOrderByMemberId> getReviewListAllByMemberId(String memberId) {

        List<ReviewOrderByMemberId> reviewOrderByMemberIdList = reviewDao.getReviewListAllByMemberId(memberId);

        if (reviewOrderByMemberIdList == null || reviewOrderByMemberIdList.isEmpty()) {
            throw new NullPointerException("가져올 리뷰 리스트가 없다");
        }
        log.info("REVIEW DETAIL  ! !!  !===>>> [{}]", reviewOrderByMemberIdList.toString());

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
            log.error("Insert Fail Value Check ! ! !");
            throw new NullPointerException("Value Check Insert Fail ! ! !");
        }
    }

    @Transactional
    public void deleteReviewByMemberId(String memberId, int detailNo) {

        if(memberId == null) {
            log.error("Member Id Is Null");
            throw new NullPointerException("Member Id Is Null ! ! !");
        }
        Member member = memberDao.getMemberByEmail(memberId);
        if (member == null || Objects.isNull(member)) {
            log.error("Not Found Member By Member Id ===>>> [{}]", memberId);
            throw new NullPointerException("Not Found Member By Member Id");
        }

        OrderDetail orderDetail = reviewDao.getOrderDetailList(detailNo);
        if (orderDetail == null || Objects.isNull(orderDetail)) {
            log.error("Not Found Order Detail ! ! ! ==>> [{}]", detailNo);
            throw new NullPointerException("Not Found Order Detail");
        }

        reviewDao.deleteReviewByMemberId(memberId, detailNo);
    }
}

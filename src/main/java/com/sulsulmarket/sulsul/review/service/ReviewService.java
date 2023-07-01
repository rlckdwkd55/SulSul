package com.sulsulmarket.sulsul.review.service;

import com.sulsulmarket.sulsul.member.dao.MemberDao;
import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import com.sulsulmarket.sulsul.mypage.dto.OrderDetail;
import com.sulsulmarket.sulsul.review.dao.ReviewDao;
import com.sulsulmarket.sulsul.review.dto.ReviewDTO;
import com.sulsulmarket.sulsul.review.dto.ReviewOrderByMemberId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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
    public void writeReview(ReviewDTO reviewDTO) {

        if(reviewDTO == null || Objects.isNull(reviewDTO)) {
            log.error("REVIEW DTO IS NULL ! ! !");
            throw new NullPointerException("데이터가 없습니다.");
        }

        MemberDTO member = memberDao.getMemberById(reviewDTO.getMEMBER_ID());
        if (member == null || Objects.isNull(member)) {
            log.error("Not Found Member Review Insert Fail ! ! !");
            throw new NullPointerException("회원 정보가 없습니다.");
        }

        OrderDetail orderDetail = reviewDao.getOrderDetailList(reviewDTO.getDETAIL_NO());
        if(orderDetail == null || Objects.isNull(orderDetail)) {
            log.error("Not Found OrderDetail Select Fail ! ! !");
            throw new NullPointerException("주문 상세 번호가 없습니다.");
        }

        if(reviewDTO.getREVIEW_CONTENT() == null || reviewDTO.getREVIEW_CONTENT().length() <= 0 || reviewDTO.getREVIEW_CONTENT() == "") {
            log.error("Review Content Is Null Insert Fail ! ! !");
            throw new NullPointerException("리뷰 작성 내용이 없습니다.");
        }

        log.info("Review Insert Success ! ! ! ===>>> [{}]", reviewDTO.toString());
        reviewDao.writeReview(reviewDTO);
    }

    /**
     * 모든 리뷰 리스트를 가져오는 메서드
     */
    public List<ReviewDTO> getReviewListAll() {

        List<ReviewDTO> reviewList = reviewDao.getReviewListAllByOrderDetail();

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
    public void updateReviewByMemberId(ReviewDTO reviewDTO) {

        if (reviewDTO == null || Objects.isNull(reviewDTO)) {
            log.error("Review Data Is Null ! ! !");
            throw new NullPointerException("Not Found Review Data Is Null");
        }

        int insertCount = reviewDao.updateReviewByMemberId(reviewDTO);
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
        MemberDTO member = memberDao.getMemberById(memberId);
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

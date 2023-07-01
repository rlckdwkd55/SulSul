package com.sulsulmarket.sulsul.review.service;

import com.sulsulmarket.sulsul.review.dao.ReviewDao;
import com.sulsulmarket.sulsul.review.dto.ReviewDTO;
import com.sulsulmarket.sulsul.review.dto.ReviewOrderByMemberId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Transactional
    public void writeReview(ReviewDTO reviewDTO) {
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
}

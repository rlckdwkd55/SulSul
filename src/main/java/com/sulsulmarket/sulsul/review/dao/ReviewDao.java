package com.sulsulmarket.sulsul.review.dao;

import com.sulsulmarket.sulsul.mypage.dto.OrderDetail;
import com.sulsulmarket.sulsul.review.dto.ReviewDTO;
import com.sulsulmarket.sulsul.review.dto.ReviewOrderByMemberId;
import com.sulsulmarket.sulsul.review.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDao implements ReviewMapper {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public int writeReview(ReviewDTO reviewDTO) {
        return reviewMapper.writeReview(reviewDTO);
    }

    @Override
    public List<ReviewDTO> getReviewListAllByOrderDetail() {
        return reviewMapper.getReviewListAllByOrderDetail();
    }

    @Override
    public List<ReviewOrderByMemberId> getReviewListAllByMemberId(String memberId) {
        return reviewMapper.getReviewListAllByMemberId(memberId);
    }

    @Override
    public OrderDetail getOrderDetailList(int detailNo) {
        return reviewMapper.getOrderDetailList(detailNo);
    }

    @Override
    public int updateReviewByMemberId(ReviewDTO reviewDTO) {
        return reviewMapper.updateReviewByMemberId(reviewDTO);
    }

    @Override
    public int deleteReviewByMemberId(String memberId, int detailNo) {
        return reviewMapper.deleteReviewByMemberId(memberId, detailNo);
    }
}

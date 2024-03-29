package com.sulsulmarket.sulsul.review.dao;

import com.sulsulmarket.sulsul.dto.order.OrderDetail;
import com.sulsulmarket.sulsul.dto.review.Review;
import com.sulsulmarket.sulsul.dto.review.ReviewOrderByMemberId;
import com.sulsulmarket.sulsul.review.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDao implements ReviewMapper {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public int writeReview(Review review) {
        return reviewMapper.writeReview(review);
    }

    @Override
    public List<Review> getReviewListAllByOrderDetail() {
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
    public int updateReviewByMemberId(Review review) {
        return reviewMapper.updateReviewByMemberId(review);
    }

    @Override
    public int deleteReviewByMemberId(String memberId, int detailNo) {
        return reviewMapper.deleteReviewByMemberId(memberId, detailNo);
    }

    @Override
    public Review getReviewByReviewNo(int reviewNo) {
        return reviewMapper.getReviewByReviewNo(reviewNo);
    }
}

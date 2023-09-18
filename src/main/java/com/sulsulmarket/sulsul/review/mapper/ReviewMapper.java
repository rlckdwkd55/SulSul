package com.sulsulmarket.sulsul.review.mapper;

import com.sulsulmarket.sulsul.dto.review.Review;
import com.sulsulmarket.sulsul.dto.review.ReviewOrderByMemberId;
import com.sulsulmarket.sulsul.dto.order.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    int writeReview(Review review);

    List<Review> getReviewListAllByOrderDetail();

    List<ReviewOrderByMemberId> getReviewListAllByEmail(String email);

    OrderDetail getOrderDetailList(int detailNo);

    int updateReviewByMemberId(Review review);

    int deleteReviewByReviewNo(int reviewNo);

    Review getReviewByReviewNo(int reviewNo);
}

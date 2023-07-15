package com.sulsulmarket.sulsul.review.mapper;

import com.sulsulmarket.sulsul.mypage.dto.OrderDetail;
import com.sulsulmarket.sulsul.review.dto.ReviewDTO;
import com.sulsulmarket.sulsul.review.dto.ReviewOrderByMemberId;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    int writeReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getReviewListAllByOrderDetail();

    List<ReviewOrderByMemberId> getReviewListAllByMemberId(String memberId);

    OrderDetail getOrderDetailList(int detailNo);

    int updateReviewByMemberId(ReviewDTO reviewDTO);

    int deleteReviewByMemberId(String memberId, int detailNo);

    ReviewDTO getReviewByReviewNo(int reviewNo);
}

package com.sulsulmarket.sulsul.review.mapper;

import com.sulsulmarket.sulsul.review.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    int writeReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getReviewListAll();
}

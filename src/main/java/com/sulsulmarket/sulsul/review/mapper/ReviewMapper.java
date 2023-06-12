package com.sulsulmarket.sulsul.review.mapper;

import com.sulsulmarket.sulsul.review.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

    int writeReview(ReviewDTO reviewDTO);
}
